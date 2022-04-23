(ns studying-clojure.aula3)

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (println "Calculando desconto de " desconto)
      (- valor-bruto desconto))
    valor-bruto))

; PREDICATE
(defn should-apply-discount?
  "Returns true if the product price was greater than $100"
  [price]
  (if (> price 100)
    true
    false))
;(println (should-apply-discount? 190))

(defn apply-discount?
  "Returns true if the product price was greater than $100"
  [price]
  (if (> price 100)
    true
    false))

(defn valor-descontado
  [original-price]
  (if (apply-discount? original-price)
    (let [discount-percentage (/ 10 100)
          discount (* original-price discount-percentage)]
      (- original-price discount))
    original-price))

(println (valor-descontado 200))

(defn apply-discount?
  [price]
  (println "Calling redefined method")
  ; method binding is made in execution time (not compilation)
  (if (> price 100)
    true))

(println (valor-descontado 900))

(defn apply-discount?
  [price]
  (println "Calling new redefined method")
  ; method binding is made in execution time (not compilation)
  (> price 100))

(println (valor-descontado 800))

; -- functional way to call

(defn mais-caro-que-cem [valor-bruto] (> valor-bruto 100))

(defn valor-descontado
  [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-deconto (/ 10 100)
          desconto (* valor-bruto taxa-de-deconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado mais-caro-que-cem 1000))
(println (valor-descontado mais-caro-que-cem 99))


; -- anonimous function

(fn [valor-bruto] (> valor-bruto 100))
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 1000))
(println (valor-descontado (fn [v] (> v 100)) 1000))
(println (valor-descontado #(> %1 100) 1000))
(println (valor-descontado #(> % 100) 1000))                ; less character, more complexity 👀


; -- lambda

(def mais-caro-que-cem? (fn [valor-bruto] (> valor-bruto 100)))
(println (valor-descontado mais-caro-que-cem? 40))

(defn estritamente-positivo?
  [valor]
  (if (> valor 0)
    true))

(defn estritamente-positivo? [valor] (> valor 0))
(defn estritamente-positivo? [v] (> v 0))
(println (estritamente-positivo? 3))

(fn [v] (> v 0))
#(> % 0)