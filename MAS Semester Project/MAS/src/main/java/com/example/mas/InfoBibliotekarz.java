package com.example.mas;

    public class InfoBibliotekarz {
        private Bibliotekarz bibliotekarz;
        private String specjalizacja;

        public InfoBibliotekarz(Bibliotekarz bibliotekarz, String specjalizacja) {
            this.bibliotekarz = bibliotekarz;
            this.specjalizacja = specjalizacja;
        }

        public Bibliotekarz getBibliotekarz() {
            return bibliotekarz;
        }

        public void setBibliotekarz(Bibliotekarz bibliotekarz) {
            this.bibliotekarz = bibliotekarz;
        }

        public String getSpecjalizacja() {
            return specjalizacja;
        }

        public void setSpecjalizacja(String specjalizacja) {
            this.specjalizacja = specjalizacja;
        }
    }


