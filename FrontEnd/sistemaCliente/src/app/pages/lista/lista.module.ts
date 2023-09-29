import { NgModule } from '@angular/core';
import { ListaComponent } from './lista.component';
import { CommonModule } from '@angular/common';
import { ListaRoutingModule } from './lista-routing.module';

@NgModule({
  declarations: [ListaComponent],
  exports: [
    CommonModule,
    ListaRoutingModule
  ]
})
export class ListaModule { }
