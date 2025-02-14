import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HorariosAllComponent } from './horarios-all.component';

describe('HorariosAllComponent', () => {
  let component: HorariosAllComponent;
  let fixture: ComponentFixture<HorariosAllComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HorariosAllComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HorariosAllComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
