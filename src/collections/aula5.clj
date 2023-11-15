(ns collections.aula5
  (:require [collections.db :as db]
            [collections.logic :as logic]))

(defn gastou-bastante?
  [info-do-usuario]
  (> (:preco-total info-do-usuario) 500))


; 📚
; Diferença entre o keep e o filter
; na documentação o filter trabalha com predicado,
; enquanto o keep trabalha com função.

; O filter retorna elementos que retornaram true para o fn aplicada
; O keep retorna / vai manter na coleção os valores do resultado da fn aplicada - exceto se retornar nil

(let [pedidos (db/todos-os-pedidos)
      resumo (logic/resumo-por-usuario pedidos)]
  (println "KEEP quem gastou bastante dentro do resumo:\n" (keep gastou-bastante? resumo))
  (println "FILTER quem gastou bastante dentro do resumo:\n" (filter #(gastou-bastante? %) resumo))
  (println "FILTER quem gastou bastante dentro do resumo:\n" (filter gastou-bastante? resumo)))

(println logic/debug-space)

(defn gastou-bastante?
  [info-do-usuario]
  (println "gastou-bastante?:" (:usuario-id info-do-usuario))
  (> (:preco-total info-do-usuario) 500))
(let [pedidos (db/todos-os-pedidos)
      resumo (logic/resumo-por-usuario pedidos)]
  (println "KEEP:" (keep gastou-bastante? resumo)))
