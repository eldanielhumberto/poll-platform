export interface User {
  id: string;
  username: string;
  email: string;
  visits: number;
  answers: number;
  surveys: number;
}

export interface UserSummary {
  id: string;
  username: string;
  email: string;
}
