const container = document.getElementById('produtosContainer');
const totalEl = document.getElementById('total');
const msgEl = document.getElementById('mensagem');
const loading = document.getElementById('loading');

let produtos = [];
let selecionados = {};

// Carregar produtos da API
async function carregarProdutos() {
  loading.classList.remove('d-none');
  try {
    const resp = await fetch('/produtos');
    produtos = await resp.json();
    renderProdutos(produtos);
  } catch (e) {
    showMessage('Erro ao carregar produtos.', 'danger');
  } finally {
    loading.classList.add('d-none');
  }
}

// Renderizar cards
function renderProdutos(list) {
  container.innerHTML = '';
  if (!list.length) {
    container.innerHTML = '<p class="text-center">Nenhum produto encontrado.</p>';
    return;
  }

  list.forEach(p => {
    const qty = selecionados[p.id]?.quantidade || 1;
    const checked = selecionados[p.id] ? 'checked' : '';
    const disabled = selecionados[p.id] ? '' : 'disabled';

    const html = `
      <div class="col-sm-6 col-lg-4">
        <div class="card card-produto h-100">
          <div class="card-body d-flex flex-column">
            <h5 class="card-title">${p.nome}</h5>
            <p class="card-text">R$ ${p.precoUnitario.toFixed(2)}</p>
            <div class="form-check mb-2">
              <input class="form-check-input" type="checkbox" id="chk-${p.id}" ${checked}>
              <label class="form-check-label" for="chk-${p.id}">Selecionar</label>
            </div>
            <input type="number" min="1" class="form-control mb-3" id="qtd-${p.id}" value="${qty}" ${disabled}>
            <button class="btn btn-sm btn-outline-primary mt-auto" id="btn-${p.id}">Atualizar</button>
          </div>
        </div>
      </div>`;
    container.insertAdjacentHTML('beforeend', html);

    // Eventos
    document.getElementById(`chk-${p.id}`).addEventListener('change', (e) => {
      if (e.target.checked) {
        selecionados[p.id] = { quantidade: 1, preco: p.precoUnitario };
        document.getElementById(`qtd-${p.id}`).disabled = false;
      } else {
        delete selecionados[p.id];
        document.getElementById(`qtd-${p.id}`).disabled = true;
      }
      atualizarTotal();
    });

    document.getElementById(`btn-${p.id}`).addEventListener('click', () => {
      const input = document.getElementById(`qtd-${p.id}`);
      const value = parseInt(input.value, 10) || 1;
      if (selecionados[p.id]) selecionados[p.id].quantidade = value;
      atualizarTotal();
    });
  });
}

// Atualizar total
function atualizarTotal() {
  const soma = Object.values(selecionados).reduce((acc, { quantidade, preco }) => acc + quantidade * preco, 0);
  totalEl.innerText = soma.toFixed(2);
}

// Mostrar mensagem
function showMessage(texto, tipo) {
  msgEl.innerHTML = `<div class="alert alert-${tipo}" role="alert">${texto}</div>`;
  setTimeout(() => msgEl.innerHTML = '', 3000);
}

// Filtro de busca
document.getElementById('search').addEventListener('input', e => {
  const termo = e.target.value.toLowerCase();
  renderProdutos(produtos.filter(p => p.nome.toLowerCase().includes(termo)));
});

// Enviar compra
document.getElementById('formCompra').addEventListener('submit', async e => {
  e.preventDefault();
  if (!Object.keys(selecionados).length) {
    showMessage('Selecione ao menos um produto.', 'warning');
    return;
  }

  const clienteId = parseInt(document.getElementById('clienteId').value);
  const descricao = document.getElementById('descricao').value;

  const body = {
    clienteId,
    descricao,
    produtos: Object.entries(selecionados).map(([id, { quantidade }]) => ({
      id: parseInt(id),
      quantidade
    }))
  };

  try {
    const resp = await fetch('/compras', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body)
    });
    if (resp.ok) {
      showMessage('✅ Compra registrada com sucesso!', 'success');
      selecionados = {};
      renderProdutos(produtos);
      atualizarTotal();
      e.target.reset();
    } else {
      showMessage('❌ Falha ao registrar.', 'danger');
    }
  } catch {
    showMessage('❌ Erro de conexão.', 'danger');
  }
});

// Iniciar
carregarProdutos();
