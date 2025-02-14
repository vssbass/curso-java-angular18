import { CommonModule, NgIf } from '@angular/common';
import { Component, input, OnInit } from '@angular/core';
import { locales } from '../../../../interfaces/locales-optica';
import { motivos } from '../../../../interfaces/motivos-optica';
import { horario } from '../../../../interfaces/horario';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { LocalService } from '../../../../core/services/local.service';
import { MotivoService } from '../../../../core/services/motivo.service';
import { HorarioService } from '../../../../core/services/horario.service';
import { CitasService } from '../../../../core/services/citas.service';
import { ActivatedRoute, Router } from '@angular/router';
import { forkJoin } from 'rxjs';
import { estados } from '../../../../interfaces/estados';


@Component({
  selector: 'app-citas-update',
  imports: [CommonModule, ReactiveFormsModule],
  standalone: true,
  templateUrl: './citas-update.component.html',
  styleUrl: './citas-update.component.css'
})
export class CitasUpdateComponent implements OnInit {
  getlocales: locales[] = [];
  getMotivos: motivos[] = [];
  getHorario: horario[] = [];
  getEstadoCita: estados[] = [];
  citaForm: FormGroup = new FormGroup({});
  loadingGift: boolean = false;
  codigoCitaEdit: string = '';
  inpupIdCitaCLiente: number = 0;

  constructor(
    private localServ: LocalService,
    private motivosServ: MotivoService,
    private horarioServ: HorarioService,
    private citaServ: CitasService,
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.formularioCita();
    this.getLocalesCombo();
    this.getMotivosCombo();
    this.getEstadosCombo();

    forkJoin({
      locales: this.localServ.getLocales(),
      motivos: this.motivosServ.getMotivos()
    }).subscribe(({ locales, motivos }) => {
      this.getlocales = locales;
      this.getMotivos = motivos;
      this.capturarDatosCita();
    });
  }

  formularioCita() {
    this.citaForm = this.fb.group({
      inputNombresCliente: ['', Validators.required],
      inputDniCliente: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      inputEmailCliente: ['', [Validators.required, Validators.email]],
      inputCelularCliente: ['', [Validators.required, Validators.pattern(/^\d{9}$/)]],
      inputLocalCita: ['', Validators.required],
      inputMotivoCita: ['', Validators.required],
      inputDateCita: ['', Validators.required],
      inputHoraCita: ['', Validators.required],
      textObservacionesCita: ['', Validators.required],
      inputEstadoCita: ['', Validators.required],
      btnActualizarCita: ['']
    });
  }

  isInvalidField(field: string): boolean {
    const control = this.citaForm.get(field);
    return !!control && control.invalid && (control.touched || control.dirty);
  }

  getFieldError(field: string): string {
    const control = this.citaForm.get(field);
    if (control?.hasError('required')) return 'Este campo es obligatorio';
    if (control?.hasError('pattern')) return 'Formato inválido';
    if (control?.hasError('email')) return 'Correo electrónico no válido';
    return '';
  }

  getEstadosCombo(){
    this.getEstadoCita = [
      {id:1, nombre:"Activo"},
      {id:2, nombre:"Inactivo"}
    ]
  }
  capturarDatosCita() {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      if (id) {
        this.getCitaData(id); // Llamar a la función para obtener la cita
      }
    });
  }

  actualizarCita(event: Event) {
    event.preventDefault();
    if (this.citaForm.invalid) {
      this.citaForm.markAllAsTouched();
      return;
    }

    this.loadingGift = true; // Muestra el GIF de carga
   
    const citaRequest = {
      id: this.citaForm.value.btnActualizarCita,
      codigo: "C00001",
      documento: this.citaForm.value.inputDniCliente,
      nombres: this.citaForm.value.inputNombresCliente,
      email: this.citaForm.value.inputEmailCliente,
      celular: this.citaForm.value.inputCelularCliente,
      estado: this.citaForm.value.inputEstadoCita == 1 ? true : false,
      locales: { id: this.citaForm.value.inputLocalCita },
      motivo: { id: this.citaForm.value.inputMotivoCita },
      observaciones: this.citaForm.value.textObservacionesCita,
      horacita: this.citaForm.value.inputHoraCita,
      fechacita: this.citaForm.value.inputDateCita,
      creador: "ADMIN"
    };
    
    this.citaServ.actualizarCita(citaRequest).subscribe({
      next: (res) => {
        setTimeout(() => {
          this.loadingGift = false;
          this.router.navigate(['/citas/buscar-citas']);
        }, 1000);
      },
      error: (err) => {
        console.error('Error al obtener la cita registrada', err);
      },
    });
  }

  getCitaData(id: number) {
  
    this.citaServ.getCita(id).subscribe({
      next: (data) => {
        this.codigoCitaEdit = data.codigo;
        const local = this.getlocales.find(l => l.id === data.locales?.id);
        const motivo = this.getMotivos.find(m => m.id === data.motivo?.id);

        this.citaForm.patchValue({
          inpupIdCitaCLiente: data.id,
          inputNombresCliente: data.nombres,
          inputDniCliente: data.documento,
          inputEmailCliente: data.email,
          inputCelularCliente: data.celular,
          inputLocalCita: local?.id,
          inputMotivoCita: motivo?.id,
          inputDateCita: data.fechacita,
          inputHoraCita: this.setHorarioCombo(data.fechacita, data.horacita),
          textObservacionesCita: data.observaciones,
          inputEstadoCita: data.estado? 1 : 2,
          btnActualizarCita: data.id
          
        });
      },
      error: (err) => {
        console.error('Error al obtener la cita', err);
      },
    });
  }

  getLocalesCombo() {
    this.localServ.getLocales().subscribe({
      next: (data) => {

        this.getlocales = data
      },
      error: (err) => {
        console.error('Error al obtener los locales', err);
      },
    });
  }

  getMotivosCombo() {
    this.motivosServ.getMotivos().subscribe({
      next: (data) => {

        this.getMotivos = data
      },
      error: (err) => {
        console.error('Error al obtener los motivos', err);
      },
    });
  }

  setHorarioCombo(fecha: string, hora: string) {
    this.horarioServ.getHorario(fecha).subscribe({
      next: (data) => {
        this.getHorario = data;
        const horario = this.getHorario.find(h => h.hora === hora);
        this.citaForm.patchValue({
          inputHoraCita: horario?.hora
        });
      },
      error: (err) => {
        console.error('Error al obtener los horarios', err);
      },
    });
  }

  getHorarioCombo(event: any) {
    const fecha = event.target.value;
    this.horarioServ.getHorario(fecha).subscribe({
      next: (data) => {

        this.getHorario = data
        if (this.getHorario.length > 0) {
          this.citaForm.patchValue({
            inputHoraCita: ""
          });
        }
      },
      error: (err) => {
        console.error('Error al obtener los horarios', err);
      },
    });
  }
}
