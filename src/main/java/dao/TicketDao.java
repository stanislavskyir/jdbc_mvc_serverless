package dao;

import entity.Ticket;
import exception.DaoException;
import utils.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class TicketDao implements Dao<Long, Ticket> {

    private final static TicketDao INSTANCE = new TicketDao();

    public static TicketDao getInstance(){
        return INSTANCE;
    }

    private TicketDao(){}

    private final static String SAVE_SQL = """
            INSERT INTO ticket(passport_no, passenger_name, flight_id, seat_no, cost)
            VALUES (?, ?, ?, ?, ?)
            """;

    @Override
    public boolean update(Ticket ticket) {
        return false;
    }

    @Override
    public List<Ticket> findAll() {
        return List.of();
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Ticket save(Ticket ticket) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, ticket.getPassportNumber());
            preparedStatement.setString(2, ticket.getPassengerName());
            preparedStatement.setLong(3, ticket.getFlight().getId());
            preparedStatement.setString(4, ticket.getSeatNumber());
            preparedStatement.setBigDecimal(5, ticket.getCost());

            preparedStatement.executeQuery();
            var keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                ticket.setId(keys.getLong("id"));
            }

            return ticket;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
