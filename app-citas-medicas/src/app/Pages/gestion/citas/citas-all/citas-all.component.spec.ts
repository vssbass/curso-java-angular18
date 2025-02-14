import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CitasAllComponent } from './citas-all.component';

describe('CitasAllComponent', () => {
  let component: CitasAllComponent;
  let fixture: ComponentFixture<CitasAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CitasAllComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CitasAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
