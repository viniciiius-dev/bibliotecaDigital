let conteudo_global = null;
let nomes = [];
const body = document.body;

async function PegarArquivo() {
  try {
    const resposta = await fetch("scripts/BD de teste de livros.json");

    const conteudo = await resposta.json();

    nomes = conteudo.map(livro => livro.nome);
    conteudo_global = conteudo;
  } 
  catch (e) {
    console.log("Erro ao carregar o arquivo: " + e);
  }
}

async function main() {

await PegarArquivo();

let n_linhas = 5;
let n_quadrados = 10;
let id_jaaparecido = [];

const generos = ["Romance", "Distopia", "Ficção Científica", "Cyberpunk", "Fantasia"];

for (let i = 0; i < generos.length; i++) {
  const genero = generos[i];

  // Título do gênero
  const tituloGenero = document.createElement("h1");
  tituloGenero.textContent = genero + ":";
  body.appendChild(tituloGenero);

  const linha = document.createElement("div");
  linha.classList.add("linhas");
  body.appendChild(linha);

  for (let j = 0; j < n_quadrados; j++) {
    // Sorteia ANTES de criar o quadrado
    const candidatos = conteudo_global.filter(
      l => l.genero === genero && !id_jaaparecido.includes(l.id)
    );
    if (candidatos.length === 0) break; // acabaram os livros desse gênero

    const livro = candidatos[Math.floor(Math.random() * candidatos.length)];
    id_jaaparecido.push(livro.id);

    const quadrado = document.createElement("div");
    quadrado.classList.add("quadrados");
    linha.appendChild(quadrado);

    // Imagem do livro
    const img = document.createElement("img");
    img.src = livro.foto;
    img.loading = 'lazy';
    img.alt = "Capa de " + livro.nome;
    img.classList.add("icone-pesquisa");
    quadrado.appendChild(img);

    // Nome do livro
    const nomeLivro = document.createElement("h2");
    nomeLivro.textContent = livro.nome;
    quadrado.appendChild(nomeLivro);

    // Autor do livro
    const autorLivro = document.createElement("p");
    autorLivro.textContent = "Autor: " + livro.autor;
    quadrado.appendChild(autorLivro);

    // Gênero do livro
    const generoLivro = document.createElement("p");
    generoLivro.textContent = "Gênero: " + livro.genero;
    quadrado.appendChild(generoLivro);

    // Status do livro
    const statusLivro = document.createElement("span");
    statusLivro.textContent = "Status: " + livro.status;
    if (livro.status === "indisponível") {
      statusLivro.style.backgroundImage = "linear-gradient(to right, rgb(255, 58, 58) 50%, rgb(255, 32, 118))";
    }
    quadrado.appendChild(statusLivro);
  }
}

//Clicar na imagem para exibir informações do livro
const imagens = document.querySelectorAll('img');

imagens.forEach(imagem => {
  imagem.addEventListener('click', (evento) => {
    const livroInfo = conteudo_global.find(l => l.foto === evento.target.src);
    if (livroInfo) 
    {
      localStorage.setItem('livroSelecionado', JSON.stringify(livroInfo));
      window.location.href = 'pagina_livro.html';
    } 
    else {
      alert('Livro não encontrado.');
    }
  
  });
});

//Pesquisa de livros
document.getElementById("pesquisar").addEventListener("input", function() {

  const termo = this.value.toLowerCase();
  if (!termo) {
    document.getElementById("resultados").innerHTML = "";
    return;
  }
  const resultados = nomes.filter(nome => nome[0].toLowerCase() == termo[0].toLowerCase() && nome.toLowerCase().includes(termo));

  const resultadosContainer = document.getElementById("resultados");
  resultadosContainer.innerHTML = "";

  resultados.forEach(nome => {
    const resultadoItem = document.createElement("li");
    resultadoItem.textContent = nome;
    resultadoItem.className = "resultado-item";
    resultadosContainer.appendChild(resultadoItem);
  });

  resultadosContainer.addEventListener("click", function (e) {
      const itemClicado = e.target.closest("li");

      if (itemClicado) {
        document.getElementById("pesquisar").value = itemClicado.textContent.trim();
      }
    });
});


}
main();