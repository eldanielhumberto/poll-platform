import { Plus } from 'lucide-react';

import { Question } from '@/interfaces/Question';
import { Survey } from '@/interfaces/Survey';

import { QuestionEditor } from '@/components/QuestionEditor';
import { Button } from '@/components/ui/button';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';

interface Props {
  survey: Survey;
  addQuestion: () => void;
  updateQuestion: (questionId: string, updates: Partial<Question>) => void;
  deleteQuestion: (questionId: string) => void;
  moveQuestion: (questionId: string, direction: 'up' | 'down') => void;
}

export default function QuestionsSectionEditor({
  survey,
  addQuestion,
  updateQuestion,
  deleteQuestion,
  moveQuestion,
}: Props) {
  return (
    <Card>
      <CardHeader>
        <div className="flex items-center justify-between">
          <div>
            <CardTitle>Preguntas ({survey.questions.length})</CardTitle>
            <CardDescription>
              Agrega y configura las preguntas de tu encuesta
            </CardDescription>
          </div>
          <Button onClick={addQuestion}>
            <Plus className="h-4 w-4 mr-2" />
            Agregar Pregunta
          </Button>
        </div>
      </CardHeader>
      <CardContent>
        {survey.questions.length === 0 ? (
          <div className="text-center py-12 border-2 border-dashed border-gray-300 rounded-lg">
            <div className="text-gray-500">
              <Plus className="h-12 w-12 mx-auto mb-4 opacity-50" />
              <p className="text-lg font-medium mb-2">No hay preguntas aún</p>
              <p className="text-sm">
                Agrega tu primera pregunta usando el botón de arriba
              </p>
            </div>
          </div>
        ) : (
          <div className="space-y-4">
            {survey.questions.map((question, index) => (
              <QuestionEditor
                key={question.id}
                question={question}
                index={index}
                totalQuestions={survey.questions.length}
                onUpdate={(updates) => updateQuestion(question.id, updates)}
                onDelete={() => deleteQuestion(question.id)}
                onMove={(direction) => moveQuestion(question.id, direction)}
              />
            ))}
          </div>
        )}
      </CardContent>
    </Card>
  );
}
