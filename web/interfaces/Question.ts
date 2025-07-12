import { Option } from './Option';

export interface Question {
  id: string;
  questionText: string;
  options?: Option[];
}
