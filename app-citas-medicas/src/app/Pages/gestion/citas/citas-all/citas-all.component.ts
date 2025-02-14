import { Component, OnInit } from '@angular/core';
import { CitasService } from '../../../../core/services/citas.service';
import { citas } from '../../../../interfaces/citas-optica';
import { NgClass, NgFor, NgIf } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-citas-all',
  standalone: true,
  imports: [NgFor,NgIf, RouterModule, NgClass],
  templateUrl: './citas-all.component.html',
  styleUrl: './citas-all.component.css'
})
export class CitasAllComponent implements OnInit {

  listCitas: citas[] = [];
  loadingGift: boolean = false;
  expandedIndex: number | null = null;

  constructor(private citaServ: CitasService,private router: Router) {}

  ngOnInit() {
    this.getListaCitas();
  }

  getListaCitas() {
    this.citaServ.listarCitas().subscribe({
      next: (data) => {

        this.listCitas = data
      },
      error: (err) => {
        console.error('Error al obtener los locales', err);
      },
    });
  }

  editarCita(id: number) {
    this.router.navigate(['/citas/actualizar-citas/', id]);

  }
  toggleDetails(index: number) {
    this.expandedIndex = this.expandedIndex === index ? null : index;
  }
}
