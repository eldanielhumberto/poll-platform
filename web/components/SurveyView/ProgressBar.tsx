import { Progress } from '@/components/ui/progress';
import { Question } from '@/interfaces/Question';

interface Props {
  currentQuestion: number;
  questions: Question[];
}

export default function ProgressBar({ currentQuestion, questions }: Props) {
  return (
    <div className="mb-8">
      <div className="flex justify-between items-center mb-2">
        <span className="text-sm font-medium text-gray-700">
          Pregunta {currentQuestion + 1} de {questions.length}
        </span>
        <span className="text-sm text-gray-500">
          {Math.round(((currentQuestion + 1) / questions.length) * 100)}%
          completado
        </span>
      </div>
      <Progress
        value={((currentQuestion + 1) / questions.length) * 100}
        className="h-2"
      />
    </div>
  );
}
