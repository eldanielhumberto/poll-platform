import { User } from './User';

export interface Survey {
  id: string;
  title: string;
  description: string;
  visits: number;
  author: User;
  createdAt: string;
}
