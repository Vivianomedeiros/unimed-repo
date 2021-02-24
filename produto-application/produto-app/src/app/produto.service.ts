import { Injectable } from '@angular/core';
import { Produto } from './produto/model.produto';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http:HttpClient) { }

  getProdutos(): Observable<any[]>{
    return this.http.get<any[]>('http://localhost:8080/produto-api/lista-todos');
  }

  salvar(produto: Produto ): Observable<Produto>{
    return this.http.post<Produto>('http://localhost:8080/produto-api/', produto);
  }
}
