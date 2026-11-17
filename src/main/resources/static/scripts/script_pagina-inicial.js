let conteudo_global = null;
let nomes = [];
const body = document.body;
const nav = document.querySelector("nav");

async function PegarArquivo() {
  try {
    const resposta = await fetch("http://localhost:8080/book");

    const conteudo = await resposta.json();

    nomes = conteudo.map(livro => livro.nome);
    conteudo_global = conteudo;
  } 
  catch (e) {
    console.log("Erro ao carregar o arquivo: " + e);
  }
}

async function main() {

//Fazendo o botão do perfil funcionar
const btn_perfil = document.getElementById("btn_perfil");

btn_perfil.addEventListener('click', ()=>{
const elemento = document.getElementById("div_perfil");
if (!elemento)
{
const div_perfil = document.createElement("div");
div_perfil.id = "div_perfil";

div_perfil.appendChild(btn_perfil);
btn_perfil.style.right = 'auto';
btn_perfil.style.marginTop = '20px';

//Dentro da div de perfil
const ul_div_perfil = document.createElement("ul");
ul_div_perfil.className = "ul_perfil";

const li_div_perfil_INFORMACOESDACONTA = document.createElement("li");
const a_li_perfil_INFORMACOESDACONTA = document.createElement("a");
a_li_perfil_INFORMACOESDACONTA.textContent = "Informações da Conta";
a_li_perfil_INFORMACOESDACONTA.href = "pagina_perfil.html"; //Página da conta
li_div_perfil_INFORMACOESDACONTA.className = "li_perfil";
li_div_perfil_INFORMACOESDACONTA.appendChild(a_li_perfil_INFORMACOESDACONTA);

const li_div_perfil_LIVROSSALVOS = document.createElement("li");
const a_li_perfilLIVROSSALVOS = document.createElement("a");
a_li_perfilLIVROSSALVOS.textContent = "Lista de Salvos";
a_li_perfilLIVROSSALVOS.href = "pagina_salvos.html";  //Página de salvos
li_div_perfil_LIVROSSALVOS.className = "li_perfil";
li_div_perfil_LIVROSSALVOS.appendChild(a_li_perfilLIVROSSALVOS);

//Adicionando os li's na ul
ul_div_perfil.appendChild(li_div_perfil_INFORMACOESDACONTA);
ul_div_perfil.appendChild(li_div_perfil_LIVROSSALVOS);

//Adicionando a ul à div perfil
div_perfil.appendChild(ul_div_perfil)

body.appendChild(div_perfil);
}

else
{
  nav.appendChild(btn_perfil);
  btn_perfil.style.right = '50px';
  btn_perfil.style.marginTop = '0';

  elemento.remove();
}
});

await PegarArquivo();

//Funções dos favoritos
async function salvarLivro(id) {
      const response = await fetch('/api/user/saved', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify({ id: id })
      });
      const texto = await response.text();
      return texto ? JSON.parse(texto) : null;

}

async function buscarLivrosSalvos() {
      const response = await fetch('/api/user/saved', {
        method: 'GET',
        credentials: 'include' 
        
      });

      return await response.json();
}

async function deletarLivrosSalvos(id) {
        const response = await fetch('/api/user/saved', {
          method: "DELETE",
          headers: { 'Content-Type': 'application/json' },
          credentials: 'include',
          body: JSON.stringify({ id: id })
        });

          if (!response.ok) {
        const texto = await response.text();
        throw new Error(`Erro ${response.status}: ${texto}`);
        }

        return true;
      }

const livrossalvos = await buscarLivrosSalvos();
const ids_livros_salvos =  new Set (livrossalvos.map(li => li.id));

//console.log(buscarLivrosSalvos());

let n_linhas = 5;
let n_quadrados = 10;
let id_jaaparecido = [];

  const generos = [
    "DESENVOLVIMENTO",
    "TECNOLOGIA",
    "MATEMATICA",
    "ENGENHARIA",
    "CIENCIA",
    "NEGOCIOS",
    "FICCAO",
    "LITERATURA",
    "HISTORIA"
  ];
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

    //Ícone de salvar o livro
    const icone_salvar = document.createElement('button');
    icone_salvar.className = "btn_icone_salvar";
    icone_salvar.id = livro.id;

    quadrado.appendChild(icone_salvar)

    if(ids_livros_salvos.has(livro.id))
    {
      icone_salvar.classList.add('salvo');
    }

    // Imagem do livro
    const img = document.createElement("img");
    img.src = livro.foto;
    img.loading = 'lazy';
    img.alt = "Capa de " + livro.nome;
    img.classList.add("icone-pesquisa");
    quadrado.appendChild(img);
    img.dataset.id = livro.id; //linha adicionada, pois o frontend reconhecia toda imagem placeholder (imagem de livro que nao tem capa) como se fosse o mesmo livro, então por ex: livro A e B, se vc clicar em qualquer um deles, ia para a pagina do livro C


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
    const livroInfo = conteudo_global.find(l => l.id == evento.target.dataset.id); //modificada para reconhecer livro pelo ID, e não pela imagem.
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

  resultadosContainer.addEventListener("click", function (e) //Quando clicar no item
  { 
      const itemClicado = e.target.closest("li");
      console.log(itemClicado.textContent);

      if (itemClicado) {
        const livroInfo = conteudo_global.find(l => l.nome === itemClicado.textContent);
        if (livroInfo) 
        {
          localStorage.setItem('livroSelecionado', JSON.stringify(livroInfo));
          window.location.href = 'pagina_livro.html';
        } 
        else 
          {
          alert('Livro não encontrado.');
        }
      }
    });
});

//Fazendo o ícone de salvar funcionar

const btns_salvar = document.querySelectorAll('.btn_icone_salvar');


btns_salvar.forEach(salvar => {
  salvar.addEventListener('click', async (e) => {

    try 
    {
      if (salvar.classList.contains('salvo')) {
      salvar.classList.remove('salvo');
      await deletarLivrosSalvos(salvar.id);
      return;
    }
      salvar.classList.add('salvo');
      await salvarLivro(salvar.id);
    } 
    catch (erro) 
    {
      alert("Erro ao salvar o livro.");
      console.error(erro);
    }
  });
});

const footer = document.createElement("footer");
body.appendChild(footer);

const p_footer = document.createElement("p")
p_footer.textContent = "Biblioteca Digital Fatec";
footer.appendChild(p_footer);
}
main();