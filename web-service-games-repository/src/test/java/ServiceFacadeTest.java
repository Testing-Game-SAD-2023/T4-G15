import com.sad.g15.webservicegamesrepository.DataAccess.Entity.*;
import com.sad.g15.webservicegamesrepository.Exceptions.*;
import com.sad.g15.webservicegamesrepository.Service.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
class ServiceFacadeTest {
    @Mock
    private MatchService matchService;
    @Mock
    private RoundService roundService;
    @Mock
    private ResultService resultService;
    @Mock
    private PlayerService playerService;
    @Mock
    private TestCaseService testCaseService;
    @Mock
    private RobotService robotService;
    @InjectMocks
    private ServiceFacade serviceFacade;

    public ServiceFacadeTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMatch() throws PlayerNotFoundException {
        // Mocking the dependencies
        String scenario = "Test scenario";
        Match match = new Match();
        match.setScenario(scenario);
        ArrayList<Integer> idPlayers = new ArrayList<>();
        Round round = new Round();
        Result result = new Result();
        Player player = new Player();
        TestCasePlayer testCasePlayer = new TestCasePlayer();
        Robot robot = new Robot(0,"facile");
        when(robotService.readById(0)).thenReturn(robot);
        when(matchService.create(any(Match.class))).thenReturn(match);
        when(roundService.create(any(Round.class))).thenReturn(round);
        when(playerService.readById(anyInt())).thenReturn(player);
        when(resultService.create(any(Result.class))).thenReturn(result);
        when(testCaseService.create(any(TestCasePlayer.class))).thenReturn(testCasePlayer);

        // Calling the method to be tested
        Match createdMatch = serviceFacade.createMatch(idPlayers, scenario,0);
     //   System.out.println(createdMatch);
        Assertions.assertNotNull(createdMatch);
    }


    @Test
    void createRound() throws RobotNotFoundException, MatchNotFoundException {
        // Mocking the dependencies
        Match match = new Match();
        when(matchService.readSById(0)).thenReturn(match);
        Round round = new Round();
        round.setRobot(new Robot(1,"facile"));
        Round createdRound = new Round();
        when(roundService.create(round)).thenReturn(createdRound);
        Match updatedMatch = new Match();
        when(matchService.update(match)).thenReturn(updatedMatch);
        when(matchService.create(any(Match.class))).thenReturn(match);
        when(roundService.create(any(Round.class))).thenReturn(round);
        // Calling the method to be tested
        Match creaRound  = serviceFacade.createRound(match.getId(), round);
        Assertions.assertNotNull(creaRound);
    }
    @Test
    void createRound_Exception() throws RobotNotFoundException {
        // Mocking the dependencies
        Match match = new Match();
        when(matchService.readSById(1)).thenReturn(match);
        Round round = new Round();
        round.setRobot(new Robot(1, "facile"));
        Round createdRound = new Round();
        when(roundService.create(round)).thenReturn(createdRound);
        Match updatedMatch = new Match();
        when(matchService.update(match)).thenReturn(updatedMatch);
        when(matchService.create(any(Match.class))).thenReturn(match);
        when(roundService.create(any(Round.class))).thenReturn(round);
        // Calling the method to be tested
        try {
            serviceFacade.createRound(match.getId(), round);
        } catch (MatchNotFoundException e) {
            assertEquals("The given match does not exist!", e.getMessage());
        }
    }

    @Test
    void createRound_ExceptionRobot() throws MatchNotFoundException,RobotNotFoundException {
        // Mocking the dependencies
        Match match = new Match();
        when(matchService.readSById(0)).thenReturn(match);
        Round round = new Round();
        round.setRobot(new Robot(0, "facile"));
        Round createdRound = new Round();
        when(roundService.create(round)).thenReturn(createdRound);
        Match updatedMatch = new Match();
        when(matchService.update(match)).thenReturn(updatedMatch);
        when(matchService.create(any(Match.class))).thenReturn(match);
        when(roundService.create(any(Round.class))).thenReturn(round);
        // Calling the method to be tested
        try {
            serviceFacade.createRound(match.getId(), round);
        } catch (RobotNotFoundException e) {
            assertEquals("Robot not found", e.getMessage());
        }
    }

    @Test
    public void testUpdateRound() throws MatchNotFoundException {
        // Preparazione dei dati di test
        int idMatch = 123;
        int idRound = 456;
        int idRobot = 789; //Id del nuovo robot da aggiungere a round


        //Creiamo il match con id = 123
        Match match = new Match();
        when(matchService.readSById(idMatch)).thenReturn(match);

        //Creiamo il round e settiamo il robot privo di valori
        //Successivamente ritonriamo una singola istanza del match quando viene chiamato il read sui match
        Round round = new Round();
        round.setId(idRound);
        round.setRobot(new Robot());
        when(roundService.readM(match)).thenReturn(Collections.singletonList(round));

        //Simuliamo il comportamento di update round sul robot
        Round updatedRound = new Round();
        updatedRound.setId(idRound);
        updatedRound.setRobot(new Robot(idRobot,"facile"));
        when(roundService.update(round)).thenReturn(updatedRound);

        // Chiamata del metodo da testare passandogli id match e round già costruiti e il nuovo robot da aggiungere
        Round result = serviceFacade.updateRound(idMatch, idRound, LocalDateTime.now());

        // Verifichiamo che il nuovo robot di result(Nuovo round aggiornato) sia uguale a quello desiderato
        Assert.assertEquals(idRobot, result.getRobot().getId());
    }

    @Test
    void testCasePlayerCreateRoundNull() throws PlayerNotFoundException, MatchNotFoundException, TestNotFoundException {
        Round round = new Round();
        round.setRobot(new Robot(0, "facile"));
        Round createdRound = new Round();
        Match match = new Match();
        when(matchService.create(match)).thenReturn(match);
        when(roundService.create(round)).thenReturn(createdRound);
        when(matchService.readSById(0)).thenReturn(match);
        TestCasePlayer testCase = new TestCasePlayer();
        when(testCaseService.create(testCase)).thenReturn(testCase);
        try {
            Round testCaseCreate = serviceFacade.addTestCasePlayer(0,1,1,testCase);
            fail("Expected RuntimeException to be thrown");
        } catch (RoundNotFoundException e) {
            assertEquals("The given match does not contain the given round!", e.getMessage());
        }
    }

    @Test
    public void testCasePlayerCreatePlayerNull() throws RoundNotFoundException, MatchNotFoundException, TestNotFoundException {
        Match match = new Match();
        match.setId(1);

        // Crea un oggetto Round di esempio
        Round round = new Round();
        round.setId(1);

        // Crea un oggetto Player di esempio
        Player player = new Player();
        player.setId(1);

        // Crea un oggetto TestCasePlayer di esempio
        TestCasePlayer testCasePlayer = new TestCasePlayer();

        // Aggiungi il round all'elenco dei round del match
        ArrayList<Round> rounds = new ArrayList<>();
        rounds.add(round);
        match.setRounds(rounds);

        // Simula la chiamata al metodo readSById del MatchService
        when(matchService.readSById(1)).thenReturn(match);

        // Simula la chiamata al metodo readById del RoundService
        when(roundService.readById(1)).thenReturn(round);

        // Simula la chiamata al metodo readById del PlayerService
        when(playerService.readById(1)).thenReturn(player);

        // Simula la chiamata al metodo create del TestCaseService
        when(testCaseService.create(any(TestCasePlayer.class))).thenReturn(testCasePlayer);

        try {
            Round updatedRound = serviceFacade.addTestCasePlayer(1, 1, 1, testCasePlayer);
            fail("Expected RuntimeException to be thrown");
        } catch (PlayerNotFoundException e) {
            assertEquals("The given player does not partecipate in this match!", e.getMessage());
        }

    }


    @Test
    void createTestCasePlayer() throws RoundNotFoundException, PlayerNotFoundException, MatchNotFoundException, TestNotFoundException {
        Match match = new Match();
        match.setId(1);

        // Crea un oggetto Round di esempio
        Round round = new Round();
        round.setId(1);

        // Crea un oggetto Player di esempio
        Player player = new Player();
        player.setId(1);

        // Crea un oggetto TestCasePlayer di esempio
        TestCasePlayer testCasePlayer = new TestCasePlayer();

        // Aggiungi il round all'elenco dei round del match
        ArrayList<Round> rounds = new ArrayList<>();
        rounds.add(round);
        match.setRounds(rounds);

        ArrayList<Result> results = new ArrayList<>();
        Result result = new Result();
        result.setPlayer(player);
        results.add(result);
        match.setResults(results);

        when(matchService.readSById(1)).thenReturn(match);

        // Simula la chiamata al metodo readById del RoundService
        when(roundService.readById(1)).thenReturn(round);

        // Simula la chiamata al metodo readById del PlayerService
        when(playerService.readById(1)).thenReturn(player);

        // Simula la chiamata al metodo create del TestCaseService
        when(testCaseService.create(any(TestCasePlayer.class))).thenReturn(testCasePlayer);
        when(roundService.update(any(Round.class))).thenReturn(round);

        Round testCase = serviceFacade.addTestCasePlayer(1,1,1,testCasePlayer);
    }

    @Test
    void createTestCaseRobotRoundException() throws MatchNotFoundException, TestNotFoundException {
        Round round = new Round();
        round.setRobot(new Robot(0, "facile"));
        Round createdRound = new Round();
        Match match = new Match();
        when(matchService.create(match)).thenReturn(match);
        when(roundService.create(round)).thenReturn(createdRound);
        when(matchService.readSById(0)).thenReturn(match);
        TestCaseRobot testCase = new TestCaseRobot();
        when(testCaseService.create(testCase)).thenReturn(testCase);
        try {
            Round testCaseCreate = serviceFacade.createTestCaseRobot(0,1,testCase);
            fail("Expected RuntimeException to be thrown");
        } catch (RoundNotFoundException e) {
            assertEquals("The given match does not contain the given round!", e.getMessage());
        }
    }

    @Test
    void createTestCaseRobot() throws RoundNotFoundException, MatchNotFoundException, TestNotFoundException {
        Match match = new Match();
        match.setId(1);

        // Crea un oggetto Round di esempio
        Round round = new Round();
        round.setId(1);

        // Crea un oggetto Player di esempio
        Player player = new Player();
        player.setId(1);

        // Crea un oggetto TestCasePlayer di esempio
        TestCasePlayer testCasePlayer = new TestCasePlayer();

        // Aggiungi il round all'elenco dei round del match
        ArrayList<Round> rounds = new ArrayList<>();
        rounds.add(round);
        match.setRounds(rounds);



        TestCaseRobot testCaseRobot = new TestCaseRobot();
        when(matchService.readSById(1)).thenReturn(match);

        // Simula la chiamata al metodo readById del RoundService
        when(roundService.readById(1)).thenReturn(round);
        when(roundService.update(any(Round.class))).thenReturn(round);

        when(testCaseService.create(any(TestCaseRobot.class))).thenReturn(testCaseRobot);

        Round testcaseCreate = serviceFacade.createTestCaseRobot(1,1,testCaseRobot);
        Assertions.assertNotNull(testcaseCreate);
    }


    @Test
    void testUpdateMatch() throws MatchNotFoundException {
        Match match = new Match();
        match.setId(1);

        Match updateMatch = new Match();
        updateMatch.setId(1);
        updateMatch.setScenario("scenario");
        updateMatch.setEndDate(LocalDateTime.parse("2023-06-12T10:00:00"));
        when(matchService.update(match)).thenReturn(updateMatch);
        when(matchService.readSById(1)).thenReturn(match);
        Match upMatch = serviceFacade.updateMatch(1, updateMatch);
        Assertions.assertNotNull(upMatch);
    }
    @Test
    void testUpdateMatchScenario() throws MatchNotFoundException {
        Match match = new Match();
        match.setId(1);

        Match updateMatch = new Match();
        updateMatch.setId(1);
        updateMatch.setScenario("scenario");
        updateMatch.setEndDate(LocalDateTime.parse("2023-06-12T10:00:00"));
        when(matchService.update(match)).thenReturn(updateMatch);
        when(matchService.readSById(1)).thenReturn(match);
        Match upMatch = serviceFacade.updateMatch(1, updateMatch);
        Assertions.assertEquals("scenario",upMatch.getScenario());
    }
@Test
    void testUpdateMatchEndDate() throws MatchNotFoundException {
        Match match = new Match();
        match.setId(1);

        Match updateMatch = new Match();
        updateMatch.setId(1);
        updateMatch.setScenario("scenario");
        updateMatch.setEndDate(LocalDateTime.parse("2023-06-12T10:00:00"));
        when(matchService.update(match)).thenReturn(updateMatch);
        when(matchService.readSById(1)).thenReturn(match);
        Match upMatch = serviceFacade.updateMatch(1, updateMatch);
        Assertions.assertEquals(LocalDateTime.parse("2023-06-12T10:00:00"),upMatch.getEndDate());
    }


    @Test
    void readPlayerResultTestExceptionP() throws ResultNotFoundException {
        Result result = new Result();
        try {
            serviceFacade.readResultIdPlayer(1);

        }catch (PlayerNotFoundException e){
            Assertions.assertEquals("Player not found", e.getMessage());
        }
    }

    @Test
    void readPlayerResultTestExceptionR() throws PlayerNotFoundException {
        Player player = new Player(1,"matteo");
        when(playerService.readById(1)).thenReturn(player);
        try {
            serviceFacade.readResultIdPlayer(1);

        }catch (ResultNotFoundException e){
            Assertions.assertEquals("No result available for this player, yet", e.getMessage());
        }
    }

    @Test
    void readPlayerResultTest() throws PlayerNotFoundException, ResultNotFoundException {
        Player player = new Player(1,"Matteo");
        when(playerService.readById(1)).thenReturn(player);
        Result result = new Result();
        ArrayList<Result> res = new ArrayList<>();
        res.add(result);
        when(resultService.readResultByIdPlayer(1)).thenReturn(res);
        List<Result> listaRisultati = serviceFacade.readResultIdPlayer(1);
        Assertions.assertEquals(listaRisultati,res);

    }

   @Test
   void readMTestCasesTestRoundException() throws TestNotFoundException, MatchNotFoundException {
        Match match = new Match();
        when(matchService.readSById(0)).thenReturn(match);
        try{
            serviceFacade.readMTestCases(0,1);
        }catch (RoundNotFoundException e){
            Assertions.assertEquals("Round not found for Match given in input",e.getMessage());
        }
   }

   @Test
    void readMTestCasesTestExecption() throws RoundNotFoundException, MatchNotFoundException {
        Match match = new Match();
        when(matchService.readSById(0)).thenReturn(match);
        Round round = new Round();
        match.setRound(round);
        List<Round>rounds = new ArrayList<>();
        rounds.add(round);
        when(roundService.readM(match)).thenReturn(rounds);
        when(roundService.readById(0)).thenReturn(round);
        try{
            serviceFacade.readMTestCases(0,0);
        }catch (TestNotFoundException t){
            Assertions.assertEquals("No Test associated to given Round", t.getMessage());
        }

   }


   @Test
    void readMTestCasesTest() throws RoundNotFoundException, TestNotFoundException, MatchNotFoundException {
       Match match = new Match();
       when(matchService.readSById(0)).thenReturn(match);
       Round round = new Round();
       match.setRound(round);
       List<Round>rounds = new ArrayList<>();
       rounds.add(round);
       when(roundService.readM(match)).thenReturn(rounds);
       when(roundService.readById(0)).thenReturn(round);
       TestCase test = new TestCase();
       List<TestCase> testCases = new ArrayList<>();
       testCases.add(test);
       when(roundService.getTestCases(round)).thenReturn(testCases);
       List<TestCase> testCaseList = serviceFacade.readMTestCases(0,0);
       Assertions.assertEquals(testCaseList,testCases);
   }



   @Test
    void deleteRoundByIdTestMatchException(){
        try{
            serviceFacade.deleteMatchById(0);
        }catch(MatchNotFoundException e){
            Assertions.assertEquals("Match not found", e.getMessage());
       }
   }

   @Test
    void deleteRoundByIdTest() throws MatchNotFoundException {
        Match match = new Match();
        when(matchService.readSById(0)).thenReturn(match);
        when(matchService.deleteById(0)).thenReturn(true);
        Boolean result = serviceFacade.deleteMatchById(0);
        Assertions.assertTrue(result);
   }

   @Test
    void deleteMatchByIdTestException(){
       try{
           serviceFacade.deleteMatchById(0);
       }catch(MatchNotFoundException e) {
           Assertions.assertEquals("Match not found", e.getMessage());
       }
   }

   @Test
    void deleteMatchByIdTest() throws MatchNotFoundException {
        Match match = new Match();
        when(matchService.readSById(0)).thenReturn(match);
        when(matchService.deleteById(0)).thenReturn(true);
       Boolean result =  serviceFacade.deleteMatchById(0);
        Assertions.assertTrue(result);
   }
}


