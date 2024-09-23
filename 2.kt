import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TelaListaProdutos(produtos: List<Produto>, onDetalhesProduto: (Produto) -> Unit) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(produtos) { produto ->
            Column(modifier = Modifier.padding(8.dp)) {
                Text("${produto.nome} (${produto.quantidade} unidades)")
                Button(onClick = { onDetalhesProduto(produto) }) {
                    Text("Detalhes")
                }
            }
        }
    }
}
@Composable
fun AppInventario() {
    var telaAtual by remember { mutableStateOf("cadastro") }
    var produtos by remember { mutableStateOf(listOf<Produto>()) }
    var produtoSelecionado by remember { mutableStateOf<Produto?>(null) }

    when (telaAtual) {
        "cadastro" -> TelaCadastroProduto { produto ->
            produtos = produtos + produto
            telaAtual = "lista"
        }
        "lista" -> TelaListaProdutos(produtos) { produto ->
            produtoSelecionado = produto
            telaAtual = "detalhes"
        }
        "detalhes" -> produtoSelecionado?.let {
            TelaDetalhesProduto(it) { telaAtual = "lista" }
        }
    }
}
