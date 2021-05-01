import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookFileComponent } from './book-file.component';

describe('BookFileComponent', () => {
  let component: BookFileComponent;
  let fixture: ComponentFixture<BookFileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookFileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookFileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
