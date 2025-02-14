import { Component, OnInit } from '@angular/core';
import { locales } from '../../../../interfaces/locales-optica';
import { LocalService } from '../../../../core/services/local.service';
import { motivos } from '../../../../interfaces/motivos-optica';
import { MotivoService } from '../../../../core/services/motivo.service';
import { horario } from '../../../../interfaces/horario';
import { HorarioService } from '../../../../core/services/horario.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CitasService } from '../../../../core/services/citas.service';

@Component({
  selector: 'app-citas-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './citas-register.component.html',
  styleUrl: './citas-register.component.css'
})
export class CitasRegisterComponent implements OnInit {
  getlocales: locales[] = [];
  getMotivos: motivos[] = [];
  getHorario: horario[] = [];
  citaForm: FormGroup = new FormGroup({});
  loadingGift: boolean = false;

  constructor(
    private localServ: LocalService,
    private motivosServ: MotivoService,
    private horarioServ: HorarioService,
    private citaServ: CitasService,
    private fb: FormBuilder,
    private router: Router,

  ) { }

  ngOnInit() {
    this.getLocalesCombo();
    this.getMotivosCombo();
    this.formularioCita();
  }

  formularioCita() {
    this.citaForm = this.fb.group({
      inputNombresCliente: ['', Validators.required],
      inputDniCliente: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      inputEmailCliente: ['', [Validators.required, Validators.email]],
      //inputCelularCliente: ['', [Validators.required, Validators.pattern(/^\+51\s9\d{8}$/)]],
      inputCelularCliente: ['', [Validators.required, Validators.pattern(/^\d{9}$/)]],
      inputLocalCita: ['', Validators.required],
      inputMotivoCita: ['', Validators.required],
      inputDateCita: ['', Validators.required],
      inputHoraCita: ['', Validators.required],
      textObservacionesCita: ['', Validators.required]
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

  registrarCita(event: Event) {
    event.preventDefault();
    if (this.citaForm.invalid) {
      this.citaForm.markAllAsTouched();
      return;
    }

    this.loadingGift = true; // Muestra el GIF de carga

    const citaRequest = {
      id: 0, // Suponiendo que es una nueva cita
      codigo: "C00001",
      documento: this.citaForm.value.inputDniCliente,
      nombres: this.citaForm.value.inputNombresCliente,
      email: this.citaForm.value.inputEmailCliente,
      celular: this.citaForm.value.inputCelularCliente,
      estado: true, 
      locales: { id: this.citaForm.value.inputLocalCita },
      motivo: { id: this.citaForm.value.inputMotivoCita },
      observaciones: this.citaForm.value.textObservacionesCita,
      horacita: this.citaForm.value.inputHoraCita,
      fechacita: this.citaForm.value.inputDateCita,
      creador: "ADMIN" 
    };
    
    this.citaServ.registrarCita(citaRequest).subscribe({
      next: (res) => {
        setTimeout(() => {
          this.loadingGift = false;
          this.router.navigate(['/citas/buscar-citas']);
        }, 1000);
        console.log('Cita registrada', res);
      },
      error: (err) => {
        console.error('Error al obtener la cita registrada', err);
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

  getHorarioCombo(event: any) {
    const fecha = event.target.value;
    this.horarioServ.getHorario(fecha).subscribe({
      next: (data) => {

        this.getHorario = data.filter(h => h.estado === true);
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
