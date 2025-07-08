import { Clock, Users } from 'lucide-react';
import Link from 'next/link';
import dayjs from 'dayjs';

import { Survey as ISurvey } from '@/interfaces/Survey';

import { Avatar, AvatarFallback } from '@/components/ui/avatar';
import { Button } from '@/components/ui/button';
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';

export default function Survey({
  id,
  title,
  description,
  author,
  createdAt,
  answers,
}: ISurvey) {
  return (
    <Card className="border-0 shadow-sm hover:shadow-md transition-shadow">
      <CardHeader>
        <div className="flex items-start justify-between mb-2">
          <Badge className="bg-blue-100 text-blue-800">Tecnologia</Badge>
          {/* <div className="flex items-center space-x-1">
            <Star className="h-4 w-4 text-yellow-500 fill-current" />
            <span className="text-sm">{survey.rating}</span>
          </div> */}
        </div>
        <CardTitle className="text-lg line-clamp-2">{title}</CardTitle>
        <CardDescription className="line-clamp-3">
          {description}
        </CardDescription>
      </CardHeader>
      <CardContent>
        <div className="flex items-center justify-between mb-4">
          <div className="flex items-center space-x-2">
            <Avatar className="h-6 w-6">
              <AvatarFallback className="text-xs">
                {author.username
                  .split(' ')
                  .map((n) => n[0])
                  .join('')}
              </AvatarFallback>
            </Avatar>
            <span className="text-sm text-gray-600">{author.username}</span>
          </div>
          <div className="flex items-center text-xs text-gray-500">
            <Clock className="h-3 w-3 mr-1" />
            {dayjs(createdAt).fromNow()}
          </div>
        </div>
        <div className="flex items-center justify-between">
          <div className="flex items-center text-sm text-gray-600">
            <Users className="h-4 w-4 mr-1" />
            {answers.toLocaleString()}
          </div>
          <Link href={`/survey/${id}`}>
            <Button size="sm" variant="outline">
              Ver Encuesta
            </Button>
          </Link>
        </div>
      </CardContent>
    </Card>
  );
}
