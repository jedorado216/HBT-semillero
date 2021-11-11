import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-gestionar-compra',
  templateUrl: './gestionar-compra.component.html',
  styleUrls: ['./gestionar-compra.component.css']
})
export class GestionarCompraComponent implements OnInit {

  public gestionarComicForm: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { 
    this.gestionarComicForm = this.fb.group({
      cantidad: [null, Validators.required],
    });
  }

  ngOnInit() {
  }
  public comprarComic(posicion :number) : void {
    
  }

}
