import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BienvenidaComponent } from './semillero/componentes/home/bienvenida-component';
import { GestionarComicComponent} from './semillero/componentes/gestionar-comic/gestionar-comic.component';
import { ModalComponent} from './semillero/componentes/modal/modal.component';
import { GestionarCompraComponent} from './semillero/componentes/gestionar-compra/gestionar-compra.component';
const routes: Routes = [
  { path: 'bienvenida', component: BienvenidaComponent, data : null },
  { path: 'gestionar-comic', component: GestionarComicComponent },
  { path: 'modal', component: ModalComponent },
  { path: 'gestionar-compra', component: GestionarCompraComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
