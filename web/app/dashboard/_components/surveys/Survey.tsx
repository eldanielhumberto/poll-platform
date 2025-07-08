'use client';

import { Calendar, Eye, MoreHorizontal, Users } from 'lucide-react';
import Link from 'next/link';
import dayjs from 'dayjs';

import removeSurvey from '@/actions/surveys/removeSurvey';

import { Survey as ISurvey } from '@/interfaces/Survey';

import { Button } from '@/components/ui/button';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';

export default function Survey({
  title,
  description,
  visits,
  createdAt,
  answers,
  id,
}: ISurvey) {
  return (
    <div className="flex items-center justify-between p-4 border rounded-lg hover:bg-gray-50 transition-colors">
      <div className="flex-1">
        <div className="flex items-center space-x-3 mb-2">
          <h3 className="font-semibold text-gray-900">{title}</h3>
        </div>
        <p className="text-gray-600 text-sm mb-3">{description}</p>
        <div className="flex items-center space-x-6 text-sm text-gray-500">
          <div className="flex items-center space-x-1">
            <Users className="h-4 w-4" />
            <span>{answers} respuestas</span>
          </div>
          <div className="flex items-center space-x-1">
            <Eye className="h-4 w-4" />
            <span>{visits} vistas</span>
          </div>
          <div className="flex items-center space-x-1">
            <Calendar className="h-4 w-4" />
            <span>Creada el {dayjs(createdAt).format('DD MMMM YYYY')}</span>
          </div>
        </div>
      </div>
      <div className="flex items-center space-x-2">
        <Link href={`/my-survey/${id}`}>
          <Button variant="outline" size="sm">
            Ver Resultados
          </Button>
        </Link>
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" size="sm">
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <Link href={`/edit-survey/${id}`}>
              <DropdownMenuItem>Editar</DropdownMenuItem>
            </Link>
            <DropdownMenuItem>Compartir</DropdownMenuItem>
            <DropdownMenuItem
              className="text-red-600"
              onClick={() => removeSurvey(id)}
            >
              Eliminar
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      </div>
    </div>
  );
}
