import { Component, OnInit } from '@angular/core';
import { horario } from '../../../../interfaces/horario';
import { HorarioService } from '../../../../core/services/horario.service';
import { NgClass, NgFor } from '@angular/common';

@Component({
  selector: 'app-horarios-all',
  standalone: true,
  imports: [NgFor, NgClass],
  templateUrl: './horarios-all.component.html',
  styleUrl: './horarios-all.component.css'
})
export class HorariosAllComponent implements OnInit {
  //listHorario: horario[] = [];
  listHorario: { fecha: string; horarios: horario[] }[] = [];
  constructor(private horarioService: HorarioService) { }
  ngOnInit(): void {
    this.getListHorarios();
  }

  getListHorarios() {
    this.horarioService.getHorarioAll().subscribe({
      next: (data) => {
        this.listHorario = this.agruparPorFecha( data);
        //console.log(this.listHorario);
      },
      error: (err) => {
        console.error('Error al obtener los locales', err);
      },
    });
  }

  agruparPorFecha(data: horario[]) {
    const agrupado: { [fecha: string]: horario[] } = {};

    data.forEach(horario => {
      if (!agrupado[horario.fecha]) {
        agrupado[horario.fecha] = [];
      }
      agrupado[horario.fecha].push(horario);
    });

    return Object.keys(agrupado).map(fecha => ({
      fecha,
      horarios: agrupado[fecha]
    }));
  }
}
