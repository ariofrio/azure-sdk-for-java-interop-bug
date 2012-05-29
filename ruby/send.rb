require 'waz-queues'

options = { :account_name => "devstoreaccount1",
            :access_key => "Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==",
            :base_url => "127.0.0.1:10001",
            :use_devenv => true }

WAZ::Storage::Base.establish_connection! options

puts "Sending message..."
queue = WAZ::Queues::Queue.ensure "ariofrio-language-interop-test-queue"
queue.enqueue! "This is the message: hey there, didn't see you there."
