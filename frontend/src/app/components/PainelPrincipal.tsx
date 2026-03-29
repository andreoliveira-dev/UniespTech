import * as React from 'react';
import { Calendario } from './Calendario';
import { Eventos } from './Eventos';
import { CartaoAcao } from './CartaoAcao';
import { Sparkles, ArrowRight, User, BarChart3 } from 'lucide-react';

interface PainelPrincipalProps {
  nomeUsuario?: string;
}

export function PainelPrincipal({ nomeUsuario }: PainelPrincipalProps) {
  const estaAutenticado = !!nomeUsuario;
  
  const dadosUsuario = {
    nome: nomeUsuario || "Visitante",
    cargo: estaAutenticado ? "Estudante" : "Faça login para continuar",
    fotoUrl: "https://images.unsplash.com/photo-1648170723309-46a266549e73?crop=entropy&cs=tinysrgb&fit=facearea&facepad=2&w=256&h=256&q=80" // Gatinho fofinho
  };

  return (
    <div className="w-full h-full bg-[#111113]/50 backdrop-blur-2xl border border-white/[0.08] rounded-[3rem] p-6 sm:p-8 lg:p-10 flex flex-col gap-6 sm:gap-8 relative overflow-hidden shadow-[inset_0_1px_2px_rgba(255,255,255,0.1),0_32px_80px_rgba(0,0,0,0.8)] ring-1 ring-white/5">
      
      <div className="absolute inset-x-0 top-0 h-[2px] bg-gradient-to-r from-transparent via-white/20 to-transparent pointer-events-none" />
      <div className="absolute inset-y-0 left-0 w-[1px] bg-gradient-to-b from-white/10 to-transparent pointer-events-none" />

      <div className="flex-1 min-h-0 flex flex-col gap-6 sm:gap-8 overflow-y-auto custom-scrollbar pr-1">
        
        {/* Seção Superior */}
        <div className="grid grid-cols-1 lg:grid-cols-4 gap-6 sm:gap-8 shrink-0">
          <div className="lg:col-span-3 w-full rounded-[2.5rem] bg-gradient-to-br from-rose-500/15 via-orange-500/5 to-transparent border border-white/10 p-8 sm:p-10 relative overflow-hidden shadow-[inset_0_1px_2px_rgba(255,255,255,0.15)] flex flex-row items-center justify-between min-h-[160px]">
            <div className="absolute -top-24 -right-12 w-64 h-64 bg-rose-500/20 rounded-full blur-3xl pointer-events-none" />
            <div className="absolute bottom-0 right-1/4 w-32 h-32 bg-orange-400/10 rounded-full blur-2xl pointer-events-none" />
            
            <div className="relative z-10 flex flex-col justify-center">
              <div className="flex items-center gap-3 mb-3">
                <span className="flex items-center justify-center w-8 h-8 rounded-full bg-rose-500/20 border border-rose-500/30 text-rose-300">
                  <Sparkles className="w-4 h-4" />
                </span>
                <span className="text-sm font-semibold tracking-widest text-rose-300/80 uppercase">Semestre 2026.1</span>
              </div>
              <h2 className="text-3xl sm:text-4xl font-semibold tracking-tight text-zinc-50 capitalize">
                {estaAutenticado ? (
                  <>Bem-vindo(a), <span className="text-transparent bg-clip-text bg-gradient-to-r from-rose-300 to-orange-200">{dadosUsuario.nome}!</span></>
                ) : (
                  <>Olá, <span className="text-transparent bg-clip-text bg-gradient-to-r from-zinc-300 to-zinc-500">Aguardando login...</span></>
                )}
              </h2>
            </div>

            <div className="relative z-10 flex items-center gap-4 hidden sm:flex bg-black/40 p-2.5 pr-6 rounded-full border border-white/5 backdrop-blur-md shadow-inner">
              <div className="w-14 h-14 rounded-full bg-zinc-800 border-2 border-white/10 overflow-hidden shrink-0 flex items-center justify-center">
                {estaAutenticado ? (
                  <img src={dadosUsuario.fotoUrl} alt={dadosUsuario.nome} className="w-full h-full object-cover" />
                ) : (
                  <User className="w-6 h-6 text-zinc-500" />
                )}
              </div>
              <div className="flex flex-col">
                <span className="text-white font-medium text-[15px] tracking-wide capitalize">
                  {estaAutenticado ? dadosUsuario.nome : "Aguardando login..."}
                </span>
                <span className="text-zinc-500 font-bold text-[10px] uppercase tracking-wider mt-0.5">
                  {dadosUsuario.cargo}
                </span>
              </div>
            </div>
          </div>

          <div className="lg:col-span-1 w-full rounded-[2.5rem] bg-white/[0.03] border border-white/[0.08] p-8 flex flex-col items-center justify-center relative overflow-hidden shadow-[inset_0_1px_1px_rgba(255,255,255,0.05)] hover:bg-white/[0.05] transition-colors cursor-pointer group">
            {estaAutenticado ? (
              <>
                <h3 className="text-5xl font-bold text-zinc-100 group-hover:scale-105 transition-transform">650</h3>
                <span className="text-sm font-medium text-zinc-500 mt-2">Pontuação TRI</span>
                <div className="absolute bottom-4 right-4 opacity-0 group-hover:opacity-100 transition-opacity">
                  <ArrowRight className="w-5 h-5 text-rose-300" />
                </div>
              </>
            ) : (
              <div className="flex flex-col items-center text-center text-zinc-500">
                <BarChart3 className="w-8 h-8 mb-3 opacity-50" />
                <span className="text-xs font-medium uppercase tracking-wider">TRI Indisponível</span>
              </div>
            )}
          </div>
        </div>

        {/* Grade do Meio */}
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 sm:gap-8 flex-1 min-h-[440px]">
          <div className="lg:col-span-2 flex flex-col h-full shrink-0">
            <Calendario />
          </div>
          <div className="lg:col-span-1 flex flex-col h-full shrink-0">
            <Eventos />
          </div>
        </div>

        {/* Cartões de Ação */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 sm:gap-8 h-[240px] shrink-0">
          <CartaoAcao 
            titulo="Rematrícula"
            descricao="Portal do Aluno"
            urlImagem="https://images.unsplash.com/photo-1769699369445-263a7a365df7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx1bml2ZXJzaXR5JTIwY2FtcHVzJTIwYWVyaWFsfGVufDF8fHx8MTc3NDU2NjExNnww&ixlib=rb-4.1.0&q=80&w=1080"
          />
          <CartaoAcao 
            titulo="Meus Cursos"
            descricao="Ambiente Virtual"
            urlImagem="https://images.unsplash.com/photo-1766297247924-6638d54e7c89?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxzdHVkZW50cyUyMGNvbXB1dGVyJTIwbGFifGVufDF8fHx8MTc3NDU2NjExNXww&ixlib=rb-4.1.0&q=80&w=1080"
          />
          <CartaoAcao 
            titulo="Secretaria"
            descricao="Atendimento"
            urlImagem="https://images.unsplash.com/photo-1752650733890-8d99f5a4bc70?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx1bml2ZXJzaXR5JTIwb2ZmaWNlJTIwc3RhZmZ8ZW58MXx8fHwxNzc0NTY2MTIwfDA&ixlib=rb-4.1.0&q=80&w=1080"
          />
        </div>

      </div>
    </div>
  );
}
