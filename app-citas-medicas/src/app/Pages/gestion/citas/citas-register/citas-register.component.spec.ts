import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CitasRegisterComponent } from './citas-register.component';

describe('CitasRegisterComponent', () => {
  let component: CitasRegisterComponent;
  let fixture: ComponentFixture<CitasRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CitasRegisterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CitasRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
