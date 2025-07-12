import { User } from './User';

export interface Survey {
  id: string;
  title: string;
  description: string;
  category: {
    name: string;
    color: string;
  };
  visits: number;
  answers: number;
  author: User;
  createdAt: string;
}
