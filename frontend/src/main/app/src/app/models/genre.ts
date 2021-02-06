import {Book} from './book';

export interface Genre {
  id: number;
  desc: string;
  books: Book[];
}
