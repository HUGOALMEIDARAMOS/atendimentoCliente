import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'listaCliente', loadChildren : () => import('./pages/lista/lista.module').then(m => m.ListaModule)},
  {path: 'cadastroCliente', loadChildren : () => import('./pages/cadastro/cadastro.module').then(c => c.CadastroModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
