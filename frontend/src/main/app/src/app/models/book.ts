import {Genre} from './genre';
import {Author} from './author';

export class Book {
  id: number;
  title: string;
  text: string;
  status: string;
  genre: Genre[];
  authors: Author[];
  photo: number;
  photoURL: string | ArrayBuffer;
  fileURL: string | ArrayBuffer;
}
