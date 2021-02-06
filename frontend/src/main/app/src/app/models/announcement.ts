export interface Announcement {
  id: number;
  desc: string;
  date: Date;
  status: boolean;
  bookId: number;
  ownerId: number;
  adminId: number;
}
