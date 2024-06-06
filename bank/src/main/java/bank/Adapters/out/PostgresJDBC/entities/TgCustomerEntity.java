package bank.Adapters.out.PostgresJDBC.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TgCustomerEntity {
    @Id
    private Long chatId;
    private Long customerId;

    public TgCustomerEntity(Long customerId, Long chatId) {
        this.customerId = customerId;
        this.chatId = chatId;
    }

    public TgCustomerEntity() {}

    public Long getChatId() {
        return chatId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}
