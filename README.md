# EmpleoTrack
EmpleoTrack es una aplicación en **Java y Spring Boot** que busca ofertas de trabajo de programación descartando las que ya he visto y me envía las nuevas directamente al móvil por Telegram.

##Motivo
Pensé en una forma de poder encontrar vacantes de prácticas sin tener que meterme tanto a cualquier aplicación de búsqueda de empleo, sobretodo en Linkedin, aunque al final lo he realizado con la API de Adzuna debido a que la de Linkedin no era gratuita. Y con este proyecto he conseguido automatizar el proceso mientras aprendo el stack estándar de la industria (Spring Boot, integraciones con APIs externas y Docker).

##Tecnologías usadas:
- Java, Spring Boot
- PostgreSQL y Docker
- API's de Adzuna y Telegram

##Funcionamiento:
1. Con el temporizador (@Schedule) se ejecuta cada cierto tiempo el código (no lo he configurado del todo)
2. Búsqueda: Llama a la API de Adzuna buscando ofertas de trabajo con palabras clave
3. Persistencia: Con PostgreSQL se revisa si la oferta de trabajo existe ya en la base de datos (con un ID) y si no existe se guarda.
4. Notificación: Si se trata de una oferta de trabajo nueva, se realiza un POST hacia la API de Telegram para que nos notifique con el nombre del puesto y el link

##Test
Para probarlo en local, únicamente se necesita configurar el archivo de application.properties:
- adzuna.app-id=TU_APP_ID
- adzuna.app-key=TU_APP_KEY
- telegram.bot-token=TU_TOKEN_DE_TELEGRAM
- telegram.chat-id=TU_CHAT_ID
Y por último levantar la base de datos con docker abriendo la terminal y dirigiendote al archivo del proyecto:
docker-compose up -d
