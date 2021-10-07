/*
Escola: Etec Abdias do Nascimento
Curso: 3° Desenvolvimento de Sistemas
Equipe Fearless2077 com o robô Feraless 2077
integrantes da equipe: Fabiano Barros Rocha, Luiz Felipe Carvalho Leite e Isaac Dennis Sombra Moreira Alves
*/

package fearless2077;
/*aqui importamos tudo o que precisamos para o nosso robô*/
import robocode.*;
import robocode.util.Utils;
import static robocode.util.Utils.normalRelativeAngle;
import java.awt.Color;


/*aqui declaramos uma clsse publica FEARLESS2077 que herda da classe AdvancedRobot*/
public class FEARLESS2077 extends AdvancedRobot {
    /*aqui estamos declarando a maioria dos atributos que o nosso robô vai ter para facilitar a compreenção do código e a criação de uma lógica.*/
    double campoComprimento;
    double campoLargura;
    double vezesDesviadas;
    double larguraRobo;
    double anguloInimigo;
    double anguloRobo;
    boolean inimigoDetectado = false;
    double comprimentoRobo;
    double energiaInimigaInicial = 100;
    double positionX;
    double positionY;
    double distanciaInimiga;
    double anguloRadar;
    double radar;
    double a = 0;
    long Tempo;
    double velocidadeInimiga;

    //aqui estamos usando polimorfismo sobreecrevendo o método run()
    @Override
    public void run() {
        //aqui estamos colocando colocando as cores no nosso robô.
        setColors(Color.BLACK, Color.YELLOW, Color.BLACK, Color.RED, Color.BLACK);


        //aqui estamos falando pro radar do robô virar para esquerda com um valor de 1/0 que é o infinito positivo.
        setTurnRadarLeftRadians(Double.POSITIVE_INFINITY);
        //enquanto verdade, ou seja, em quanto o robô estiver vivo faça:
        while (true) {
            /*aqui atribuimos os valores nos atributos, valores que são métodos nativos no robocode.*/
            campoComprimento = this.getBattleFieldHeight();
            campoLargura = this.getBattleFieldWidth();
            larguraRobo = this.getWidth();
            comprimentoRobo = this.getHeight();
            positionX  = this.getX();
            positionY = this.getY();
            anguloRobo = this.getHeading();
            anguloRadar = getRadarHeading();
            Tempo = getTime();

            ahead(0.1);//ande 0.1 pixels para frente.

            //os dois if para desviar da parede, pegamos a posição do nosso robô para evita com que ele se colida com a parede.
            /*if(positionX < 100 || positionX > 700){
                desviaParede();//aqui puxamos o método desviaParede que foi criado mais para baixo e foi explica mais em baixo do código

            }
            if(positionY < 100 || positionY > 500){
                desviaParede();//aqui puxamos o método desviaParede que foi criado mais para baixo e foi explica mais em baixo do código
            }*/
        }



    }


    //Código para desviar da parede, evitar uma colisão e perda de energia
    /*public void desviaParede() {
        setAhead(100);//anda para frente a 100 pixels.
        setTurnLeft(200);//vira pra esquerda a 200 pixels.
    }*/




    //aqui criamos um método publico que retonar vaizio, com o nome tiroPrevio, como os parametros double anguloInimigo, double distanciaInimiga, double anguloAbdoluto e quando o inimigo for escaneado nós chamamos esse método.
    public void tiroPrevio(double anguloInimigo, double distanciaInimiga, double anguloAbsoluto, double velocidadeInimiga){

        /*se o angulo do inimigo for menor que -91 nós sabemos que ele está indo a esquerda do nosso robô. se o angulo do inimigo for maior que -89 sabemos que ele está indo para a direita do nosso robô
        pois o nosso robo está a -90 graus em relação ao inimigo. e será visto essa parte do código mais pra frente.*/
        if(anguloInimigo < -9.0 && distanciaInimiga < 250.0 /*&& velocidadeInimiga == -8*/){//se o angulo do inimigo for menor que menos 91 e a distancia do inimigo for menor que 250 pixels faça:
            setTurnGunRightRadians(-0.5 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));/*vire o canhão a esquerda em radianos a -0.5 graus mais o valor de anguloAbsoto menos o
            angulo do canhão em radianos normalizado com um método do robocode que será explicado mais pra frente. */
            //System.out.println(-0.5 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));
            tiro();//aqui ele chama o método tiro que será explicado mais pra frente.

        }
        if(anguloInimigo > -89.0 && distanciaInimiga < 250.0 /*&& velocidadeInimiga == 8*/) {//se o angulo do inimigo for maior que menos 89 e a distancia do inimigo for menor que 250 pixels faça:
            setTurnGunRightRadians(0.5 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));/*vire o canhão a direita em radianos a 0.5 graus mais o valor de anguloAbsoto menos o
            angulo do canhão em radianos normalizado com um método do robocode que será explicado mais pra frente. */
            //System.out.println(0.5 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));
            tiro();//aqui ele chama o método tiro que será explicado mais pra frente.

        }
        if(anguloInimigo < -91.0 && distanciaInimiga < 300.0 && distanciaInimiga >=250 /*&& velocidadeInimiga == -8*/) {//se o angulo do inimigo for menor que menos 91 e a distancia do inimigo for menor que 300 pixels faça:
            setTurnGunRightRadians(-0.6 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));/*vire o canhão a direnta em radianos a -0.6 graus mais o valor de anguloAbsoto menos o
            angulo do canhão em radianos normalizado com um método do robocode que será explicado mais pra frente. */
            //System.out.println(-0.6 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));
            tiro();//aqui ele chama o método tiro que será explicado mais pra frente.

        }
        if(anguloInimigo > -89.0 && distanciaInimiga < 300.0 && distanciaInimiga >=250 /*&& velocidadeInimiga == 8*/) {//se o angulo do inimigo for maior que menos 89 e a distancia do inimigo for menor que 300 pixels faça:
            setTurnGunRightRadians(0.6 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));/*vire o canhão a direnta em radianos a 0.6 graus mais o valor de anguloAbsoto menos o
            angulo do canhão em radianos normalizado com um método do robocode que será explicado mais pra frente. */
            //System.out.println(0.6 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));
            tiro();//aqui ele chama o método tiro que será explicado mais pra frente.

        }
        if(anguloInimigo < -91.0 && distanciaInimiga < 350.0 && distanciaInimiga >=300 /*&& velocidadeInimiga == -8*/) {//se o angulo do inimigo for menor que menos 91 e a distancia do inimigo for menor que 350 pixels faça:
            setTurnGunRightRadians(-0.3 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));/*vire o canhão a direnta em radianos a -0.5 graus mais o valor de anguloAbsoto menos o
            angulo do canhão em radianos normalizado com um método do robocode que será explicado mais pra frente. */
            //System.out.println(distanciaInimiga);
            //System.out.println(-0.5 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));

            tiro();//aqui ele chama o método tiro que será explicado mais pra frente.

        }
        if(anguloInimigo > -89.0 && distanciaInimiga < 350.0 && distanciaInimiga >=300 /*&& velocidadeInimiga == 8*/) {//se o angulo do inimigo for maior que menos 89 e a distancia do inimigo for menor que 3500 pixels faça:
            setTurnGunRightRadians(0.3 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));/*vire o canhão a direnta em radianos a 0.5 graus mais o valor de anguloAbsoto menos o
            angulo do canhão em radianos normalizado com um método do robocode que será explicado mais pra frente. */
            //System.out.println(distanciaInimiga);
            //System.out.println(0.5 + robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));
            tiro();//aqui ele chama o método tiro que será explicado mais pra frente.

        }
        if(anguloInimigo <= -89.0 && anguloInimigo >= -91.0){//se o angulo do inimigo for menor que menos 91 e a distancia do inimigo for menor que 250 pixels faça:
            setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));/*vire o canhão a direnta em radianos a um valor de anguloAbsoto menos o
            angulo do canhão em radianos normalizado com um método do robocode que será explicado mais pra frente. */
            //System.out.println(robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));
            tiro();//aqui ele chama o método tiro que será explicado mais pra frente.
        }

    }





    //aqui criamos um método publico que retorna vazio, como o nome de tiro e sem nenhum parâmetros.
    public void tiro(){
        //Quando nosso robô estiver numa distância curta do adversário (Menos que 200 pixels), irá atirar com força 3
        if(distanciaInimiga < 200){
            setFire(3);
        }
        //Quando nosso robô estiver numa distância intermediária do adversário (De 200 a 300 pixels), irá atirar com força 2
        if(distanciaInimiga >= 200 && distanciaInimiga < 300){
            setFire(2);
        }
        //Quando nosso robô estiver numa distância alta (De 300 a 500 pixels), irá atirar com força 1
        if(distanciaInimiga >= 300 && distanciaInimiga < 500){
            setFire(1);
        }
        //Quando nosso robô estiver numa distância Longa (De 300 a 500 pixels), irá atirar com força 0.5
        if(distanciaInimiga >= 500){
            setFire(0.5);
        }
        //Quando nosso robô estiver com menos de 3 pontos de energia, irá atirar com força 0.1
        if(getEnergy() < 3.0){
            setFire(0.1);
        }
    }


    /*aqui é temos um evento do robocode que é quando escaneamos o inimigo*/
    public void onScannedRobot (ScannedRobotEvent inimigo){

        /*aqui viramos o nosso radar para esquerda em radianos usando o valor do método getRadarTurnRemainingRadians() que nos retorna o angulo restante na curva do radar.*/
        setTurnRadarLeftRadians(getRadarTurnRemainingRadians());



        /*logo abaixo criamos a variável anguloAbsoluto que ira guardar o valor de um calculo para manter o inimigo na nossa mira do canhão, no cálculo nós pegamos o angulo do nosso
        robô em radianos e somamos com o angulo do inimigo em radianos. */
        double anguloAbsoluto = getHeadingRadians() + inimigo.getBearingRadians();

        /*logo abaixo viramos o nosso canhão a direita em radianos usando um metódo do robocode que é o normalRelativeAngle, que normaliza ou coloca valores equivalentes
        em angulos para menor ou igual a PI e  maior que -PI. Dentro desse método nós colocamos o valor de um angulo como um resultado de um cálculo, sendo o valor de anguloAbsoluto
        menos o angulo do nosso canhão em radianos.*/
        setTurnGunRightRadians(robocode.util.Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians()));

        inimigoDetectado = true;//Detecta o adversário
        anguloInimigo = inimigo.getBearing();//Detecta o ângulo do oponente
        distanciaInimiga = inimigo.getDistance();//Detecta a distância do adversário
        velocidadeInimiga = inimigo.getVelocity();
        System.out.println(velocidadeInimiga);
        //System.out.println(anguloInimigo);

        tiroPrevio(anguloInimigo, distanciaInimiga, anguloAbsoluto, velocidadeInimiga);//aqui chamamos o metodo explicado acima, com as variáveis necessárias para o mesmo funcionar.



        /*Deixa nosso robô a 90° do robô adversário a uma distância menor que 400 pixels, pra quando
        o robô do oponente atirar, nosso robô possa desviar usando um método de ir para frente e para atrás.*/

        if(distanciaInimiga < 400) {//se distancia do inimigo for menor que 400 faça:
            setTurnRight(90 - Math.abs(anguloInimigo));//vire para direita em um angulo de 90 graus menos o angulo do inimigo

        }
        else {//se não acontecer o primeiro if faça:
            setTurnRight(40 - Math.abs(anguloInimigo));//vire à direta em um angulo de 40 graus menos o angulo inimigo.
            setAhead(50);//ande 50 pixels para frente.
        }









        //criamos essa variável para facilitar o entendimento e a criação da lógica.
        boolean tiroDetectado;//essa várial vai ser usada para retornar true se caso o inimigo disparar uma bala e false se caso o inimigo não disparar.
        //essa variável foi criada para que possamos descobrir quando o inimigo vai disparar contra nós.
        double diferencaEnergia = energiaInimigaInicial - inimigo.getEnergy();

        /*a lógica usada foi, quando um inimigo dispara ele perde uma energia em um valor acima de 0 até 3, então criamos a variavel diferencaEnergia que quando um inimigo
        perder energia ele vai guardar um valor correspondete ao calculo que é, a enegiaInimigaInicial que sempre vai ser resetada quando o inimigo for escaneado, ou seja,
        sempre que o inimigo perder energia, a nova energia vai ser armazenada na varial energiaInimigaInicial. Logo depois subtraimos a energiaInimigainicial com a nova energia,
        usando o método inimigo.getEnergy(); para retornar a energia atual do nosso inimigo. Então nós guardamos esse valor na várial, quando chega no if, nós verificamos se esse valor
        corresponde a resultado de acima de 0 e menor ou igual a 3, pois no robocode quando atiramos o robô perde a energia a qual equivale a força da bala, sendo as forças possíveis
        valores acima de 0 e menor ou igual a 3.*/

        if (diferencaEnergia > 0 && diferencaEnergia <= 3){
            tiroDetectado = true;//se a condição do if for igual a true ele entra aqui, e então a nossa variável tiroDetectado vai receber o valor true.
            vezesDesviadas++;//e colocamos um contador para um calculo futuro.
        } else {//se caso a condição do if for igual a false ele entra aqui
            tiroDetectado = false;//nos colocamos o valor false nessa varial, para que se por um acaso o inimigo perder energia diferente a condição, o nosso robô não venha tentar deviar.
        }


        energiaInimigaInicial = inimigo.getEnergy();//essa variável está recebendo inimigo.getEnergy para atualizar o valor da energia Inicial.



        /*O nosso robô anda uma vez frente e a próxima vez ele vai para atrás, afim de desviar dos tiros do adversário,
        para caso do oponente tentar prevê nosso movimento, nós possamos
        recuar, evitando o tiro*/

        /*no primeiro if além do tiroDetectado que tem que retonar true, nós fazemos um calculo para ver se o valor vai ser impar ou par, se for par ele entra no if, se for impar
        eles entra no else if se também obtver um valor igual a true em sua condição*/
        if (tiroDetectado && vezesDesviadas % 2 == 0){
            if(distanciaInimiga <= 300) {//esse if verifica se a distancia do inimigo é menor ou igual a 300 pixels.
                setAhead(-150);//se a condição do if for igual a true ele vai andar -150 pixels.
            }
            if(distanciaInimiga > 300){//verifica se a distancia inimiga é maior que 300.
                setAhead(-100);//se for maior que 300 ele anda -100 pixels.
            }

        } else if(tiroDetectado){//verifica se o tiro foi detectado
            if(distanciaInimiga <= 300) {//se tiro for detectado e o valor do calculo do primeiro if retornar false ele entra nesse outro if. Esse if verifica se a distancia inimiga for menor ou igual a 300 pixels.
                setAhead(150);//se a condição do primeiro if for aceito ele vai andar a 150 pixels.
            }
            if(distanciaInimiga > 300){//verifica se a distancia inimiga é maior que 300 pixels.
                setAhead(100);//se for maior ele vai andar 100 pixels para frente.
            }
        }
        //Desviar dos tiros do adversário
    }

}
