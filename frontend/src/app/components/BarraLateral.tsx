import * as React from 'react';
import { Home, BookOpen, CalendarDays, BarChart3, Settings, HelpCircle, LogOut, GraduationCap } from 'lucide-react';
import { useNavigate } from 'react-router';

const LINKS_NAVEGACAO = [
  { icone: Home, rotulo: 'Painel', ativo: true },
  { icone: BookOpen, rotulo: 'Meus Cursos' },
  { icone: CalendarDays, rotulo: 'Horário', novo: true },
  { icone: BarChart3, rotulo: 'Notas' },
  { icone: Settings, rotulo: 'Ajustes' },
];

export function BarraLateral() {
  const navegar = useNavigate();

  return (
    <aside className="w-[220px] bg-[#050505]/85 backdrop-blur-2xl flex flex-col h-full border-r border-white/5 shrink-0 relative z-20 shadow-[8px_0_30px_rgba(0,0,0,0.5)] transition-all duration-300">
      
      <div className="p-6 pb-4 flex items-center gap-3 border-b border-white/5 mb-4">
        <div className="w-8 h-8 rounded-lg bg-gradient-to-tr from-rose-500 to-orange-400 flex items-center justify-center shadow-[0_0_15px_rgba(244,63,94,0.3)] shrink-0">
          <GraduationCap className="w-5 h-5 text-white" />
        </div>
        <span className="text-zinc-100 font-bold tracking-wide text-lg">Uniesp</span>
      </div>

      <nav className="flex-1 px-4 py-2 space-y-3 overflow-y-auto custom-scrollbar">
        {LINKS_NAVEGACAO.map((link, i) => (
          <a key={i} href="#" className={`flex items-center gap-3 py-2 transition-colors ${link.ativo ? 'text-rose-400 font-medium' : 'text-zinc-500 hover:text-zinc-200'} ${link.novo ? 'group' : ''}`}>
            <link.icone className="w-4 h-4" strokeWidth={link.ativo ? 2 : 1.5} />
            <span className={`text-sm ${link.novo ? 'flex-1' : ''}`}>{link.rotulo}</span>
            {link.novo && (
              <span className="text-[9px] font-bold tracking-wider bg-rose-500/10 text-rose-400 px-1.5 py-0.5 rounded-md uppercase">Novo</span>
            )}
          </a>
        ))}
      </nav>

      <div className="p-4 mb-2 space-y-3 border-t border-white/5 pt-6">
        <a href="#" className="flex items-center gap-3 py-2 text-zinc-500 hover:text-zinc-200 transition-colors">
          <HelpCircle className="w-4 h-4" strokeWidth={1.5} />
          <span className="text-sm">Ajuda & Suporte</span>
        </a>
        <button onClick={() => navegar('/')} className="w-full flex items-center gap-3 py-2 text-zinc-500 hover:text-rose-400 transition-colors cursor-pointer">
          <LogOut className="w-4 h-4" strokeWidth={1.5} />
          <span className="text-sm">Sair da conta</span>
        </button>
      </div>
    </aside>
  );
}
