import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'modal',
  templateUrl: './modal.component.html',
})
export class ModalComponent implements OnInit {
  //@Input() name;

  constructor(private modal:NgbModal) {}

  ngOnInit(): void {
    
  }
  openCentrado(contenido): void{

  this.modal.open(contenido,{centered:true});
  }
}

