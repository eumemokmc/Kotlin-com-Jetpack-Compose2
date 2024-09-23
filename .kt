import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun TelaCadastroProduto(onProdutoCadastrado: (Produto) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }

    val contexto = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoria") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = preco,
            onValueChange = { preco = it },
            label = { Text("Preço") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (nome.isEmpty() || categoria.isEmpty() || preco.isEmpty() || quantidade.isEmpty()) {
                Toast.makeText(contexto, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                val produto = Produto(nome, categoria, preco.toDouble(), quantidade.toInt())
                onProdutoCadastrado(produto)
                Toast.makeText(contexto, "Produto cadastrado", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Cadastrar")
        }
    }
}

data class Produto(val nome: String, val categoria: String, val preco: Double, val quantidade: Int)

