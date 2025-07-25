import { CheckCircle, Share2, Users } from 'lucide-react';
import dayjs from 'dayjs';

import { Survey } from '@/interfaces/Survey';

import { Card, CardDescription, CardHeader, CardTitle } from '../ui/card';
import { Avatar, AvatarFallback } from '../ui/avatar';
import { Button } from '../ui/button';
import { Badge } from '../ui/badge';

interface Props {
  survey: Survey;
  isAnswered?: boolean;
}

export default function SurveyDetails({ survey, isAnswered }: Props) {
  return (
    <Card className="border-0 shadow-lg mb-8">
      <CardHeader>
        <div className="flex items-center justify-between mb-4">
          <div className="flex items-center gap-3">
            <Badge className={survey.category.color}>
              {survey.category.name}
            </Badge>
            {isAnswered && (
              <Badge className="bg-green-100 text-green-800 border-green-300">
                <CheckCircle className="h-4 w-4" />
                Ya contestada
              </Badge>
            )}
          </div>
          <div className="flex items-center space-x-4">
            <Button variant="ghost" size="sm">
              <Share2 className="h-4 w-4" />
            </Button>
          </div>
        </div>

        <CardTitle className="text-2xl mb-2">{survey.title}</CardTitle>
        <CardDescription className="text-base">
          {survey.description}
        </CardDescription>

        <div className="flex items-center justify-between mt-6">
          <div className="flex items-center space-x-3">
            <Avatar className="h-10 w-10">
              <AvatarFallback>
                {survey.author?.username
                  .split(' ')
                  .map((n) => n[0])
                  .join('') || 'U'}
              </AvatarFallback>
            </Avatar>
            <div>
              <p className="font-medium">{survey.author?.username || 'User'}</p>
              <p className="text-sm text-gray-500">
                Creada el {dayjs(survey.createdAt).format('DD MMMM YYYY')}
              </p>
            </div>
          </div>
          <div className="flex items-center space-x-4 text-sm text-gray-600">
            <div className="flex items-center space-x-1">
              <Users className="h-4 w-4" />
              <span>{survey.answers?.length} respuestas</span>
            </div>
          </div>
        </div>
      </CardHeader>
    </Card>
  );
}
