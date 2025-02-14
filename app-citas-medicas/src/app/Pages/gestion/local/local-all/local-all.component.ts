import { Component, OnInit } from '@angular/core';
import { locales } from '../../../../interfaces/locales-optica';
import { LocalService } from '../../../../core/services/local.service';
import { NgClass, NgFor } from '@angular/common';

@Component({
  selector: 'app-local-all',
  standalone: true,
  imports: [NgFor, NgClass],
  templateUrl: './local-all.component.html',
  styleUrl: './local-all.component.css'
})
export class LocalAllComponent implements OnInit {
  listLocales: locales[] = [];
  constructor(private localServ : LocalService) {}
  ngOnInit(): void {
    this.getListaCitas();
  }

  getListaCitas() {
    this.localServ.getLocales().subscribe({
      next: (data) => {
        this.listLocales = data
      },
      error: (err) => {
        console.error('Error al obtener los locales', err);
      },
    });
  }
}
