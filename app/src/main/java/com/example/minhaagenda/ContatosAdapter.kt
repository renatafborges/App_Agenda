package com.example.minhaagenda
//vai achar o conjunto de dados e recilar os itens
//viewholder-> criar os itens, está ligada ao adapter, criar classe dentro de outra
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Classe de adapter: vínculo entre dataset e viewholder (item)
class ContatosAdapter(val dataSet: MutableList<Contato>) : RecyclerView.Adapter<ContatosAdapter.ContatosViewHolder>() {


    //=====VIEWHOLDER======///
//Class de Viewholder: vínculo de atributos XML<>Código
// referencia entre os componentes e o código (ex: findViewById)

    class ContatosViewHolder(view: View): RecyclerView.ViewHolder(view){

        val nome = view.findViewById<TextView>(R.id.ItemNome)
        val telefone = view.findViewById<TextView>(R.id.ItemTelefone)
        val info = view.findViewById<TextView>(R.id.ItemInfo)
    }

    //=====ADAPTER======///

    //ciclo de vida vulgo método que cria o viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHolder {
        //inflar (layout),criar o layout, exibir na sua tela
        //parent, componente mãe que vai chamar o adapter
        //utilizar o adapter em qualquer lugar

        val instanciaView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contato, parent, false)
        return ContatosViewHolder(instanciaView)
    }

    //Vinculo entre dataset e item (layou)
    //substituir os itens do layout pelos itens do meu dataset
    //for each - reciclagem
    override fun onBindViewHolder(holder: ContatosViewHolder, position: Int) {
        holder.nome.text = dataSet[position].nome
        holder.telefone.text = dataSet[position].telefone
        holder.info.text = dataSet[position].info
    }
//tamanho dos itens da lista
    override fun getItemCount(): Int = dataSet.size

}