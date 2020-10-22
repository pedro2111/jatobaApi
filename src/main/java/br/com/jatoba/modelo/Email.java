package br.com.jatoba.modelo;

public class Email {

    private Orcamento orcamento;
    private Produto produto;

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Email(Orcamento orcamento, Produto produto) {
        this.orcamento = orcamento;
        this.produto = produto;
    }

    public Email(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public String montaEmail(){

        String messagem = "<h2>Orçamento Cod: "+ this.orcamento.getId()+ "</h2>"+
        "<hr />"+
        "<h4>Informações do Pedido</h4>"+
        "<table>"+
        "<tbody>"+
        "<tr>"+
        "<td>Nome</td>"+
        "<td>"+ this.orcamento.getNome()+"</td>"+
        "</tr>"+
        "<tr>"+
        "<td>Telefone</td>"+
        "<td>"+ this.orcamento.getTelefone()+"</td>"+
        "</tr>"+
        "<tr>"+
        "<td>Email</td>"+
        "<td>"+ this.orcamento.getEmail()+"</td>"+
        "</tr>"+
        "<tr>"+
        "<td>Menssagem</td>"+
        "<td>"+ this.orcamento.getMensagem()+"</td>"+
        "</tr>"+
        "<tr>"+
        "<td>Produto</td>"+
        "<td>Cod: "+ this.produto.getId()+ " - " + this.produto.getNome() + "</td>"+
        "</tr>"+
        "</tbody>"+
        "</table>";

        return messagem;
    }
    public String montaEmailSemProduto(){

        String messagem = "<h2>Orçamento Cod: "+ this.orcamento.getId()+ "</h2>"+
        "<hr />"+
        "<h4>Informações do Pedido</h4>"+
        "<table>"+
        "<tbody>"+
        "<tr>"+
        "<td>Nome</td>"+
        "<td>"+ this.orcamento.getNome()+"</td>"+
        "</tr>"+
        "<tr>"+
        "<td>Telefone</td>"+
        "<td>"+ this.orcamento.getTelefone()+"</td>"+
        "</tr>"+
        "<tr>"+
        "<td>Email</td>"+
        "<td>"+ this.orcamento.getEmail()+"</td>"+
        "</tr>"+
        "<tr>"+
        "<td>Menssagem</td>"+
        "<td>"+ this.orcamento.getMensagem()+"</td>"+
        "</tr>"+
        "</tbody>"+
        "</table>";

        return messagem;
    }



    

    

    

    

    
}
