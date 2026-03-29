import * as React from 'react';
import { useState } from 'react';

import { ChevronLeft, ChevronRight } from 'lucide-react';

const DIAS_DA_SEMANA = ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'];
const NOMES_MESES = [
  'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 
  'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'
];

export function Calendario() {
  const [dataAtual, setDataAtual] = useState(new Date(2026, 2, 10)); 
  const hojeReal = new Date();

  const ano = dataAtual.getFullYear();
  const mes = dataAtual.getMonth();

  const primeiroDiaDoMes = new Date(ano, mes, 1).getDay();
  const diasNoMes = new Date(ano, mes + 1, 0).getDate();

  // Dias com eventos simulados
  const destaques = mes === 2 ? [10, 15, 20] : mes === 3 ? [5, 12] : [];

  const alterarMes = (delta: number) => setDataAtual(new Date(ano, mes + delta, 1));
  const irParaHoje = () => setDataAtual(new Date());

  const espacosVazios = Array.from({ length: primeiroDiaDoMes }, (_, i) => i);
  const dias = Array.from({ length: diasNoMes }, (_, i) => i + 1);

  return (
    <div className="w-full h-full bg-white/[0.02] border border-white/[0.08] backdrop-blur-3xl rounded-[2.5rem] p-8 sm:p-10 shadow-[inset_0_1px_1px_rgba(255,255,255,0.05)] flex flex-col relative overflow-hidden group">
      <div className="absolute top-[-10%] right-[-10%] w-[300px] h-[300px] bg-rose-500/10 rounded-full blur-[80px] pointer-events-none group-hover:bg-rose-500/15 transition-colors duration-700" />
      <div className="absolute bottom-[-10%] left-[-10%] w-[200px] h-[200px] bg-indigo-500/10 rounded-full blur-[60px] pointer-events-none group-hover:bg-indigo-500/15 transition-colors duration-700" />

      <div className="flex items-center justify-between w-full mb-10 z-10">
        <div>
          <h3 className="text-3xl font-semibold text-zinc-50 tracking-tight flex items-center gap-2">
            {NOMES_MESES[mes]} <span className="text-zinc-400 font-medium">{ano}</span>
          </h3>
          <span className="text-zinc-500 text-sm font-medium mt-1 block">
            {destaques.length > 0 ? `Eventos agendados: ${destaques.length}` : 'Nenhum evento agendado'}
          </span>
        </div>
        <div className="flex gap-2">
          <button onClick={() => alterarMes(-1)} className="w-10 h-10 flex items-center justify-center rounded-full bg-white/[0.03] hover:bg-white/[0.08] border border-white/5 text-zinc-400 hover:text-white transition-all">
            <ChevronLeft className="w-5 h-5" />
          </button>
          <button onClick={irParaHoje} className="px-5 h-10 font-medium text-rose-300 bg-rose-500/10 hover:bg-rose-500/20 rounded-full transition-all border border-rose-500/20">
            Hoje
          </button>
          <button onClick={() => alterarMes(1)} className="w-10 h-10 flex items-center justify-center rounded-full bg-white/[0.03] hover:bg-white/[0.08] border border-white/5 text-zinc-400 hover:text-white transition-all">
            <ChevronRight className="w-5 h-5" />
          </button>
        </div>
      </div>

      <div className="w-full h-full flex flex-col justify-center z-10 max-w-2xl mx-auto">
        <div className="w-full grid grid-cols-7 gap-x-2 gap-y-4 sm:gap-y-6 text-center">
          {DIAS_DA_SEMANA.map(dia => (
            <div key={dia} className="font-semibold text-zinc-500 text-[13px] tracking-wider mb-2">{dia}</div>
          ))}
          {espacosVazios.map(b => (
            <div key={`vazio-${b}`} className="w-12 h-12" />
          ))}
          {dias.map(dia => {
            const temDestaque = destaques.includes(dia);
            const ehHoje = hojeReal.getDate() === dia && hojeReal.getMonth() === mes && hojeReal.getFullYear() === ano;
            
            return (
              <div key={`dia-${dia}`} className="flex justify-center items-center">
                <div className={`w-12 h-12 flex items-center justify-center rounded-[1.25rem] font-medium text-base transition-all duration-300 cursor-pointer ${temDestaque ? 'bg-gradient-to-tr from-rose-500 to-orange-400 text-white shadow-[0_4px_20px_rgba(244,63,94,0.4),inset_0_1px_1px_rgba(255,255,255,0.4)] font-bold scale-110' : ehHoje ? 'border border-zinc-500 text-zinc-100 font-bold bg-white/5' : 'text-zinc-300 hover:bg-white/10 hover:text-white hover:shadow-[inset_0_1px_1px_rgba(255,255,255,0.1)]'}`}>
                  {dia}
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}
