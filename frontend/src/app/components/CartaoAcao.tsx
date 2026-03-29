import * as React from 'react';
import { ArrowUpRight } from 'lucide-react';

interface CartaoAcaoProps {
  titulo: string;
  descricao: string;
  urlImagem: string;
}

export function CartaoAcao({ titulo, descricao, urlImagem }: CartaoAcaoProps) {
  return (
    <div className="group w-full h-full rounded-[2.5rem] shadow-[inset_0_1px_2px_rgba(255,255,255,0.2),0_10px_40px_rgba(0,0,0,0.4)] overflow-hidden cursor-pointer transition-all duration-700 hover:shadow-[inset_0_1px_2px_rgba(255,255,255,0.3),0_20px_50px_rgba(2fb,113,133,0.2)] hover:-translate-y-2 relative border border-white/10 bg-zinc-950">
      
      <div className="absolute inset-0 w-full h-full overflow-hidden">
        <img 
          src={urlImagem} 
          alt={titulo}
          className="w-full h-full object-cover scale-105 group-hover:scale-110 transition-transform duration-[1.5s] ease-out opacity-80"
        />
        <div className="absolute inset-0 bg-gradient-to-t from-[#09090b]/95 via-[#09090b]/40 to-transparent group-hover:via-[#09090b]/60 transition-colors duration-500" />
        <div className="absolute inset-0 bg-[radial-gradient(ellipse_at_top,_var(--tw-gradient-stops))] from-white/10 to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-700 mix-blend-overlay" />
      </div>
      
      <div className="absolute bottom-0 left-0 w-full p-8 flex items-end justify-between z-10">
        <div className="flex flex-col gap-1">
          <p className="text-rose-400 text-xs font-bold tracking-widest uppercase opacity-0 -translate-y-4 group-hover:opacity-100 group-hover:translate-y-0 transition-all duration-500 delay-100">
            {descricao}
          </p>
          <h3 className="text-zinc-50 font-bold text-2xl tracking-tight mb-1 group-hover:text-white transition-colors duration-300">
            {titulo}
          </h3>
        </div>
        
        <div className="w-12 h-12 rounded-full bg-white/[0.08] backdrop-blur-xl flex items-center justify-center border border-white/20 opacity-0 group-hover:opacity-100 scale-75 group-hover:scale-100 transition-all duration-500 delay-100 shadow-[inset_0_1px_1px_rgba(255,255,255,0.2)]">
          <ArrowUpRight className="w-5 h-5 text-white" strokeWidth={2.5} />
        </div>
      </div>
      
      <div className="absolute inset-0 bg-gradient-to-tr from-white/0 via-white/[0.05] to-white/0 translate-x-[-100%] group-hover:translate-x-[100%] transition-transform duration-1000 ease-in-out pointer-events-none" />
    </div>
  );
}
