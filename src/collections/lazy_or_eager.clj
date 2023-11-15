(ns collections.lazy-or-eager)


;; 📚 LAZY OR EAGER?

(println (range 10))
(println (take 2 (range 10000000000)))
; Nos dois casos, imprime super rápido, o que sugere que o take não está sendo eager ("guloso")
; e sim lazy, já que só gera à medida que é necessário

; como o map se comporta?
(defn filtro1 [x]
  (println "filtro1" x)
  x)

(println (map filtro1 (range 10)))

(defn filtro1 [x]
  (println "filtro1" x)
  x)

(println (map filtro1 (range 10)))

(defn filtro2 [x]
  (println "filtro2" x)
  x)

(println (map filtro2 (map filtro1 (range 10))))

; até aqui, mesmo chamando duas vezes para uma seq pequena, parece que o map é guloso. MAS
(->> (range 50)
     (map filtro1)
     (map filtro2)
     (println))
; há chunks no map (olhar dentro da fn no core) o que faz com que o map seja lazy,
; fragmentando em blocos a coll para processar


(->> (range 50)
     (mapv filtro1)
     (mapv filtro2)
     (println))
; o mapv obriga a ser eager, pois transforma a seq em um vetor (e os vetores ficam alocados
; em memoria). uma boa usar para seq finitas

;; ✅ Se o que será processado é infinito,
;; não poderemos utilizar o processo eager, afinal não haverá memória o suficiente.

;; ✅ No caso de funções com efeito colateral,
;; não é interessante mesclarmos lazy e eager,
;; afinal isso demandaria um controle fino que pode ser trabalhoso.

;; ✅ Para grupos grandes e finitos, é interessante utilizarmos o processo lazy.
;; Precisamos sempre pensar se faz sentido otimizar o programa por essa via,
;; e muitas vezes não será, pois nem sempre trabalhamos com vetores e listas de
;; um milhão de elementos, por exemplo.
;; No caso de colecoes pequenas, o map se comportam como eager e já é OK.

