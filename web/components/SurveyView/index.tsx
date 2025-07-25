'use client';

import { useState } from 'react';

import { Card, CardContent } from '@/components/ui/card';

import SurveyPendingQuestions from './SurveyPendingQuestions';
import SurveyResponses from './SurveyResponses';
import SurveyDetails from './SurveyDetails';

import { useAuth } from '@/hooks/useAuth';

import { Survey } from '@/interfaces/Survey';

interface SurveyPreviewProps {
  survey: Survey;
  handleSubmit?: (answers: Record<string, string>) => void;
  isEditor?: boolean;
}

// REFACTOR THIS COMPONENT
export function SurveyView({
  survey,
  handleSubmit,
  isEditor,
}: SurveyPreviewProps) {
  const [answers, setAnswers] = useState({} as Record<string, string>);
  const [currentQuestion, setCurrentQuestion] = useState(0);
  const { user } = useAuth();

  const handleSingleChoice = (questionText: string, value: string) => {
    setAnswers((prev) => ({ ...prev, [questionText]: value }));
  };

  const userResponses = Array.isArray(survey.answers)
    ? survey.answers.filter((v) => v.respondent.id === user?.id)
    : [];

  if (
    isEditor &&
    (survey.questions.length === 0 ||
      survey.title.length === 0 ||
      survey.description.length === 0 ||
      survey.category.name.length === 0)
  ) {
    return (
      <Card>
        <CardContent className="py-12 text-center">
          <p className="text-gray-500">
            Rellena la informacion basica y agrega preguntas para ver la vista
            previa de tu encuesta
          </p>
        </CardContent>
      </Card>
    );
  }

  const isAnswered = userResponses && userResponses?.length > 0;

  return (
    <div className="max-w-4xl mx-auto space-y-8">
      {/* Survey details */}
      <SurveyDetails survey={survey} isAnswered={isAnswered} />

      {isAnswered && user ? (
        <SurveyResponses survey={survey} respondent={user} />
      ) : (
        <SurveyPendingQuestions
          setCurrentQuestion={setCurrentQuestion}
          currentQuestion={currentQuestion}
          handleSingleChoice={handleSingleChoice}
          handleSubmit={handleSubmit}
          answers={answers}
          survey={survey}
          user={user}
        />
      )}
    </div>
  );
}
