import { Component, OnInit } from '@angular/core';
import { ProdutoService } from '../produto.service'
import { Produto } from './model.produto';



@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {

  produto: Produto;
  produtos: Produto[] = []

  produtoLimpo: Produto = {id:0, descricao:'', dataCadastro:''};


  constructor(private service: ProdutoService) {
    this.produto = {id:10, descricao:'Daniel', dataCadastro:'2021-02-24'};

   this.produtos = [{id:10, descricao:'Viviano', dataCadastro:'2021-02-24'},
    {id:10, descricao:'Viviano', dataCadastro:'2021-02-24'},
    {id:10, descricao:'Viviano', dataCadastro:'2021-02-24'}]
  }

  ngOnInit(): void {
    this.listar() ;
  }

  onSubmit(){
    console.log(this.produto);
    this.service.salvar(this.produto).subscribe( response => {
      console.log(response); } )
  }

  public salvar(){
    console.log(this.produto.descricao);
    this.service.salvar(this.produto).subscribe( response => {
      console.log(response);
      this.produto = response;
      console.log('######################################');
      console.log(this.produto);
      this.produto.dataCadastro = this.produto.dataCadastro.substring(0,10);
      this.produtos[this.produtos.length] = this.produto;
      this.produto = this.produtoLimpo;
      this.produto.descricao = '';
      console.log(this.produto);
      //this.idProdutoTela = this.produtos.length + 1;

      this.listar();
      //this.produtoForm = '';

    // this.ngOnInit();
    })

  }
  public listar(){
    this.service.getProdutos().subscribe(resposta => {
      this.produtos = resposta;
      console.log(resposta);
    //this.idProdutoTela = this.produtos.length + 1;
    });

  }
}
