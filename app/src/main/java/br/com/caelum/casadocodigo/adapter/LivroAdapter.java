package br.com.caelum.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;

public class LivroAdapter extends RecyclerView.Adapter {


    private List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_livro_par, parent, false);

        return new NossoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        Livro livro = livros.get(position);

        NossoViewHolder holder = (NossoViewHolder) viewHolder;

        holder.nome.setText(livro.getNome());


    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    private class NossoViewHolder extends RecyclerView.ViewHolder {
        public final TextView nome;

        public NossoViewHolder(View itemView) {
            super(itemView);
            this.nome = (TextView) itemView.findViewById(R.id.item_livro_nome);
        }
    }
}
