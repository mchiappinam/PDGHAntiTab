package me.mchiappinam.pdghantitab;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
//import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;

public class Main extends JavaPlugin implements Listener {
	
  ProtocolManager protocolManager;

public void onEnable() {
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    this.protocolManager = ProtocolLibrary.getProtocolManager();
    this.protocolManager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, new PacketType[] { PacketType.Play.Client.TAB_COMPLETE })
    {
      public void onPacketReceiving(PacketEvent event)
      {
    	  if (event.getPacketType() == PacketType.Play.Client.TAB_COMPLETE)
          try {
            String message = event.getPacket().getSpecificModifier(String.class).read(0);
            if(message.startsWith("/"))
              event.setCancelled(true);
          }
          catch (FieldAccessException e) {
            Main.this.getLogger().log(Level.SEVERE, "ERRO: ", e);
          }
      }
    });
	getServer().getConsoleSender().sendMessage("§3[PDGHAntiTab] §2ativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHAntiTab] §2Acesse: http://pdgh.net/");
  }

@Override
public void onDisable() {
	getServer().getConsoleSender().sendMessage("§3[PDGHAntiTab] §2desativado - Plugin by: mchiappinam");
	getServer().getConsoleSender().sendMessage("§3[PDGHAntiTab] §2Acesse: http://pdgh.net/");
}

}