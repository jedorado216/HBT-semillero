import { compileFactoryFunction } from "@angular/compiler/src/render3/r3_factory";
import { Component, OnInit, Input } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ComicDTO } from "src/app/semillero/componentes/dto/comic-dto";
import { GestionarComicService } from "src/app/semillero/componentes/service/gestionar-comic.service";
import { Router } from "@angular/router";
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
//import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
//import { ModalComponent } from 'src/app/semillero/componentes/modal/modal.component';



@Component({
  selector: 'app-gestionar-comic',
  templateUrl: './gestionar-comic.component.html'
})
export class GestionarComicComponent implements OnInit {
  public gestionarComicForm: FormGroup;

  public submitted: boolean;

  public comicDTO: ComicDTO;

  public comicDTOInfo: ComicDTO;

  public nombre: string;

  public listaComics: Array<ComicDTO>;

  public desactivar: boolean;

  public mostrarBoton: boolean;

  public idComic : number = -1;

  

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private modal:NgbModal,
    private gestionComicsService: GestionarComicService
  ) {
    this.gestionarComicForm = this.fb.group({
      nombre: [null, Validators.required],
      editorial: [null, Validators.required],
      tematicaEnum: [null],
      coleccion: [null, Validators.required],
      numeroPaginas: [null, Validators.required],
      precio: [null, Validators.required],
      autores: [null],
      color: [null],
      cantidad: [null, Validators.required],
    });
   }

  ngOnInit() {
    this.submitted = false;
    this.mostrarBoton = false;
    this.comicDTO = new ComicDTO();
    this.listaComics = new Array<ComicDTO>();
    this.consultarComics();
    /*this.gestionComicsService.consultarUnComic(2).subscribe(dataComic=>{
      console.log(dataComic);
    },error =>{
      console.log("Se ha presentado un error ", error);
    } )*/
  }



  get f() {
    return this.gestionarComicForm.controls;
  }

  public crearComic() : void {
    this.submitted = true;
    if (this.gestionarComicForm.invalid) {
      return;
    }
    this.comicDTO = new ComicDTO();
    this.comicDTO.nombre = this.gestionarComicForm.controls.nombre.value;
    this.comicDTO.editorial = this.gestionarComicForm.controls.editorial.value;
    this.comicDTO.tematicaEnum = this.gestionarComicForm.controls.tematicaEnum.value;
    this.comicDTO.coleccion = this.gestionarComicForm.controls.coleccion.value;
    this.comicDTO.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
    this.comicDTO.precio = this.gestionarComicForm.controls.precio.value;
    this.comicDTO.autores = this.gestionarComicForm.controls.autores.value;
    this.comicDTO.color = this.gestionarComicForm.controls.color.value;
    this.comicDTO.cantidad = this.gestionarComicForm.controls.cantidad.value;
    this.comicDTO.estadoEnum = "ACTIVO";

    if(this.idComic === -1){

      this.gestionComicsService.crearComic(this.comicDTO).subscribe(
        (data) => {
          this.consultarComics();
          //if (data.exitoso) {
          //this.mostrarItem = true;
          //this.mensajeEjecucion =data.mensajeEjecucion;
          //console.log(data.mensajeEjecucion);
          // this.consultarComics();
          //} else {
          //this.mostrarItem = true;
          //this.mensajeEjecucion =data.mensajeEjecucion;
          //}
        },
        (error) => {
          console.log(error);
        }
      );
    }else{
      this.comicDTO.id = this.idComic;
      //actualiazar
      this.gestionComicsService. actualizarComic(this.comicDTO).subscribe(
        (data : ComicDTO) =>{
          this.consultarComics();
        },
        (error) => {
          console.log(error);
        }
      );
    }
    this.idComic=-1;
    this.limpiarDatosComic();
  }

  private limpiarDatosComic(): void {
    this.submitted = false;
    this.gestionarComicForm.controls.nombre.setValue(null);
    this.gestionarComicForm.controls.editorial.setValue(null);
    this.gestionarComicForm.controls.tematicaEnum.setValue(null);
    this.gestionarComicForm.controls.coleccion.setValue(null);
    this.gestionarComicForm.controls.numeroPaginas.setValue(null);
    this.gestionarComicForm.controls.precio.setValue(null);
    this.gestionarComicForm.controls.autores.setValue(null);
    this.gestionarComicForm.controls.color.setValue(null);
    this.gestionarComicForm.controls.cantidad.setValue(null);
  }

  public consultarComic(posicion: number): void {
    let comic = this.listaComics[posicion];
    this.idComic = comic.id;
    this.f.nombre.setValue(comic.nombre);
    this.f.editorial.setValue(comic.editorial);
    this.f.tematicaEnum.setValue(comic.tematicaEnum);
    this.f.coleccion.setValue(comic.coleccion);
    this.f.numeroPaginas.setValue(comic.numeroPaginas);
    this.f.precio.setValue(comic.precio);
    this.f.autores.setValue(comic.autores);
    this.f.color.setValue(comic.color);
    this.f.cantidad.setValue(comic.cantidad);
  }

  public consultarDeshabilitaFormulario(posicion: number): void {
    this.consultarComic(posicion);

    this.f.nombre.disable();
    this.f.editorial.disable();
    this.f.tematicaEnum.disable();
    this.f.coleccion.disable();
    this.f.numeroPaginas.disable();
    this.f.precio.disable();
    this.f.autores.disable();
    this.f.color.disable();
    this.f.cantidad.disable();

    this.mostrarBoton = true; /*Boton permite Input*/
  }

  public activarCampos(): void {
    this.f.nombre.enable();
    this.f.editorial.enable();
    this.f.tematicaEnum.enable();
    this.f.coleccion.enable();
    this.f.numeroPaginas.enable();
    this.f.precio.enable();
    this.f.autores.enable();
    this.f.color.enable();
    this.f.cantidad.enable();
    this.limpiarDatosComic();
    this.mostrarBoton = false; /*Botón de inhabilita Input*/
  }

  public eliminarComic(posicion :number) : void {
    let comic : ComicDTO = this.listaComics[posicion];
    this.listaComics.splice(posicion,1);

    this.gestionComicsService.eliminarComic(comic.id).subscribe(
      (data) => {
        this.consultarComics();
        //if (data.exitoso) {
        //this.mostrarItem = true;
        //this.mensajeEjecucion =data.mensajeEjecucion;
        //console.log(data.mensajeEjecucion);
        // this.consultarComics();
        //} else {
        //this.mostrarItem = true;
        //this.mensajeEjecucion =data.mensajeEjecucion;
        //}
        this.limpiarDatosComic();
      },
      (error) => {
        console.log(error);
      }
    );
  }

  public actualizarComic(posicion :number) : void {

    this.consultarComic(posicion);
  }
  //open() {
  //  const modalRef = this.modalService.open(ModalComponent);
  //  modalRef.componentInstance.name = 'World';
  //}
  public consultarComics() {
    this.gestionComicsService.consultarComics().subscribe(
      (data) => {
        // if (data[0].exitoso) {
        this.listaComics = data;
        //} else {
        //  console.log(data[0].mensajeEjecucion);
        //}
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openCentrado(contenido): void{

    this.modal.open(contenido,{centered:true});
  }

  public irAComponenteGestionarCompra(comic : ComicDTO) : void {
    this.router.navigate(['gestionar-compra', comic]);
  }

}

