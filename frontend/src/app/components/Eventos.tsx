import * as React from 'react';
import { Calculator, Calendar, ClipboardList, Clock } from 'lucide-react';

const LISTA_EVENTOS = [
  { 
    data: '10/03', 
    titulo: 'Prova de Cálculo II', 
    horario: '08:00 - 10:00',
    icone: Calculator,
    corIcone: "text-rose-300",
    corTema: "from-rose-500/20 to-orange-400/10 border-rose-500/20"
  },
  { 
    data: '15/03', 
    titulo: 'Feriado Municipal', 
    horario: 'O dia todo',
    icone: Calendar,
    corIcone: "text-indigo-300",
    corTema: "from-indigo-500/20 to-violet-400/10 border-indigo-500/20"
  },
  { 
    data: '20/03', 
    titulo: 'Entrega de Trabalho', 
    horario: 'Até 23:59',
    icone: ClipboardList,
    corIcone: "text-emerald-300",
    corTema: "from-emerald-500/20 to-teal-400/10 border-emerald-500/20"
  }
];

export function Eventos() {
  return (
    <div className="w-full h-full bg-white/[0.02] border border-white/[0.08] backdrop-blur-3xl rounded-[2.5rem] p-8 sm:p-10 shadow-[inset_0_1px_1px_rgba(255,255,255,0.05)] flex flex-col relative overflow-hidden group">
      <div className="absolute top-[-10%] right-[-10%] w-[250px] h-[250px] bg-violet-500/10 rounded-full blur-[80px] pointer-events-none group-hover:bg-violet-500/15 transition-colors duration-700" />
      
      <div className="flex items-center justify-between w-full mb-10 z-10">
        <div>
          <h3 className="text-3xl font-semibold text-zinc-50 tracking-tight">Agenda</h3>
          <span className="text-zinc-500 text-sm font-medium mt-1 block">3 Próximos eventos</span>
        </div>
        <button className="text-sm font-medium text-zinc-400 hover:text-white transition-colors bg-white/5 hover:bg-white/10 px-4 py-2 rounded-full border border-white/5 cursor-pointer">
          Ver todos
        </button>
      </div>

      <div className="flex flex-col gap-5 w-full flex-1 justify-center z-10">
        {LISTA_EVENTOS.map((evento, idx) => (
          <div key={idx} className={`group flex items-center justify-between gap-5 bg-gradient-to-r ${evento.corTema} border hover:bg-white/[0.08] hover:border-white/10 p-5 rounded-3xl transition-all duration-300 cursor-pointer shadow-[inset_0_1px_1px_rgba(255,255,255,0.05)]`}>
            <div className="flex items-center gap-5">
              <div className="flex flex-col items-center justify-center bg-zinc-950/80 w-16 h-16 rounded-[1.25rem] shadow-[inset_0_1px_1px_rgba(255,255,255,0.1)] border border-white/[0.05]">
                <span className="text-[10px] font-bold text-zinc-500 uppercase tracking-wider mb-1">MAR</span>
                <span className="text-xl font-bold text-zinc-100 leading-none">{evento.data.split('/')[0]}</span>
              </div>
              <div className="flex flex-col flex-1 min-w-0">
                <span className="text-zinc-50 text-base font-semibold truncate group-hover:text-white transition-colors">{evento.titulo}</span>
                <div className="flex items-center gap-1.5 text-zinc-400 mt-1">
                  <Clock className="w-3.5 h-3.5" />
                  <span className="text-[13px] font-medium">{evento.horario}</span>
                </div>
              </div>
            </div>
            <div className="w-12 h-12 rounded-full bg-black/40 flex items-center justify-center shrink-0 border border-white/5 group-hover:bg-black/60 transition-colors shadow-inner">
              <evento.icone className={`w-[18px] h-[18px] ${evento.corIcone}`} />
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
