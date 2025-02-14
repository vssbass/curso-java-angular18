import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalUpdateComponent } from './local-update.component';

describe('LocalUpdateComponent', () => {
  let component: LocalUpdateComponent;
  let fixture: ComponentFixture<LocalUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LocalUpdateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LocalUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
